package services;

import com.example.model.Game;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.services.GameService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Mock
    private AppHelper<Game> appHelperGameMock;

    @Mock
    private FileRepository<Game> storageMock;

    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        gameService = new GameService(appHelperGameMock, storageMock);
    }

    @Test
    public void testAdd_Success() {
        Game game = new Game();
        when(appHelperGameMock.create()).thenReturn(game);
        doNothing().when(storageMock).save(game, "games.txt");

        boolean result = gameService.add();

        assertTrue(result);
        verify(appHelperGameMock, times(1)).create();
        verify(storageMock, times(1)).save(game, "games.txt");
    }

    @Test
    public void testAdd_GameIsNull() {
        when(appHelperGameMock.create()).thenReturn(null);

        boolean result = gameService.add();

        assertFalse(result);
        verify(appHelperGameMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testAdd_ExceptionThrown() {
        when(appHelperGameMock.create()).thenThrow(new RuntimeException("Creation failed"));

        boolean result = gameService.add();

        assertFalse(result);
        verify(appHelperGameMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testPrint() {
        Game game1 = new Game();
        game1.setTitle("Game 1");
        Game game2 = new Game();
        game2.setTitle("Game 2");
        List<Game> games = Arrays.asList(game1, game2);

        when(storageMock.load("games.txt")).thenReturn(games);
        when(appHelperGameMock.printList(games)).thenReturn(true);

        boolean result = gameService.print();

        assertTrue(result);
        verify(storageMock, times(1)).load("games.txt");
        verify(appHelperGameMock, times(1)).printList(games);
    }

    @Test
    public void testList() {
        Game game1 = new Game();
        game1.setTitle("Game 1");
        Game game2 = new Game();
        game2.setTitle("Game 2");
        List<Game> games = Arrays.asList(game1, game2);

        when(storageMock.load("games.txt")).thenReturn(games);

        List<Game> result = gameService.list();

        assertEquals(2, result.size());
        assertEquals("Game 1", result.get(0).getTitle());
        assertEquals("Game 2", result.get(1).getTitle());
        verify(storageMock, times(1)).load("games.txt");
    }
}