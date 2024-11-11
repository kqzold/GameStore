package apphelpers;

import com.example.apphelpers.GameAppHelper;
import com.example.interfaces.Service;
import com.example.model.Game;
import com.example.model.Genre;
import com.example.model.Studio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

public class GameAppHelperTest {

    private GameAppHelper gameAppHelper;
    private Service<Studio> studioServiceMock;
    private Service<Genre> genreServiceMock;

    @BeforeEach
    public void setUp() {
        studioServiceMock = mock(Service.class);
        genreServiceMock = mock(Service.class);
        gameAppHelper = new GameAppHelper(studioServiceMock, genreServiceMock);
    }

    @Test
    public void testCreate() {
        // Implement test logic for the create method
    }

    @Test
    public void testPrintList() {
        Game game1 = new Game();
        game1.setTitle("Game 1");
        game1.setPublishedYear(2020);
        game1.setStudios(Arrays.asList(new Studio("Studio 1")));
        game1.setGenres(Arrays.asList(new Genre("Genre 1")));

        Game game2 = new Game();
        game2.setTitle("Game 2");
        game2.setPublishedYear(2021);
        game2.setStudios(Arrays.asList(new Studio("Studio 2")));
        game2.setGenres(Arrays.asList(new Genre("Genre 2")));

        List<Game> games = Arrays.asList(game1, game2);

        boolean result = gameAppHelper.printList(games);

        assertFalse(result);
        // Additional assertions can be added here
    }
}