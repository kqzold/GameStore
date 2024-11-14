// AppHelperGameTest.java
package apphelpers;

import com.example.model.Game;
import com.example.apphelpers.AppHelperGame;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppHelperGameTest {

    @Test
    void testCreate() {
        // Mock dependencies
        FileRepository<Game> mockRepo = mock(FileRepository.class);
        Input mockInput = mock(Input.class);
        
        // Define input sequence
        when(mockInput.getInput())
                .thenReturn("Test Game")  // Название игры
                .thenReturn("Studio1,Studio2") // Студии
                .thenReturn("Genre1,Genre2") // Жанры
                .thenReturn("59,99"); // Цена
        
        // Instantiate AppHelperGame
        AppHelperGame appHelper = new AppHelperGame(mockRepo, mockInput);
        
        // Create game
        Game game = appHelper.create();
        
        // Assertions
        assertNotNull(game);
        assertEquals("Test Game", game.getName());
        assertEquals(List.of("Studio1", "Studio2"), game.getStudios());
        assertEquals(List.of("Genre1", "Genre2"), game.getGenres());
        assertEquals(59.99, game.getPrice());
        
        // Verify interactions
        verify(mockInput, times(4)).getInput();
    }
}