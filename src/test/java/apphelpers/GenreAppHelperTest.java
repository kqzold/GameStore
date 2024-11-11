package apphelpers;

import com.example.apphelpers.GenreAppHelper;
import com.example.model.Genre;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

public class GenreAppHelperTest {

    private GenreAppHelper genreAppHelper;

    @BeforeEach
    public void setUp() {
        genreAppHelper = new GenreAppHelper() {
            private int callCount = 0;
            private String[] inputs = {"Action"};

            @Override
            public String getString() {
                return inputs[callCount++];
            }
        };
    }

    @Test
    public void testCreate() {
        Genre genre = genreAppHelper.create();
        assertNotNull(genre);
        assertEquals("Action", genre.getGenre());
    }

    @Test
    public void testPrintList() {
        Genre genre1 = new Genre();
        genre1.setGenre("Action");

        Genre genre2 = new Genre();
        genre2.setGenre("Adventure");

        List<Genre> genres = Arrays.asList(genre1, genre2);

        boolean result = genreAppHelper.printList(genres);

        assertFalse(result);
        // Additional assertions can be added here if needed
    }
}