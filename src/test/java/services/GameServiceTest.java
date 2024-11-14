package services;

import com.example.model.Game;
import com.example.services.GameService;
import com.example.interfaces.AppHelper;
import com.example.interfaces.Input;
import com.example.interfaces.FileRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameServiceTest {
    private GameService gameService;
    private List<Game> games;
    private AppHelper<Game> mockAppHelperGame;
    private Input mockInputProvider;
    private FileRepository<Game> mockGameRepository;

    @BeforeEach
    public void setUp() {
        games = mock(List.class);
        mockAppHelperGame = mock(AppHelper.class);
        mockInputProvider = mock(Input.class);
        mockGameRepository = mock(FileRepository.class);

        gameService = new GameService(games, mockAppHelperGame, mockInputProvider, mockGameRepository);
    }

    @Test
    public void testAdd_Success() {
        Game newGame = new Game();
        when(mockAppHelperGame.create()).thenReturn(newGame);

        boolean result = gameService.add();

        assertTrue(result);
        verify(games).add(newGame);
        verify(mockGameRepository).save(games);
    }

    @Test
    public void testAdd_Failure() {
        when(mockAppHelperGame.create()).thenReturn(null);

        boolean result = gameService.add();

        assertFalse(result);
        verify(games, never()).add(any(Game.class));
        verify(mockGameRepository, never()).save(anyList());
    }

    @Test
    public void testPrint() {
        gameService.print();

        verify(mockAppHelperGame).printList(games);
    }

    @Test
    public void testList() {
        List<Game> loadedGames = Arrays.asList(new Game(), new Game());
        when(mockGameRepository.load()).thenReturn(loadedGames);

        List<Game> result = gameService.list();

        assertEquals(loadedGames, result);
    }

    @Test
    public void testEdit() {
        Game game = new Game();

        boolean result = gameService.edit(game);

        assertFalse(result); // Since edit is not implemented
    }

    @Test
    public void testRemove() {
        Game game = new Game();

        boolean result = gameService.remove(game);

        assertFalse(result); // Since remove is not implemented
    }
}