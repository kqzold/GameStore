package services;

import com.example.model.Genre;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.services.GenreService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GenreServiceTest {

    @Mock
    private AppHelper<Genre> appHelperGenreMock;

    @Mock
    private FileRepository<Genre> storageMock;

    private GenreService genreService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        genreService = new GenreService(appHelperGenreMock, storageMock);
    }

    @Test
    public void testAdd_Success() {
        Genre genre = new Genre();
        when(appHelperGenreMock.create()).thenReturn(genre);
        doNothing().when(storageMock).save(genre, "genres.txt");

        boolean result = genreService.add();

        assertTrue(result);
        verify(appHelperGenreMock, times(1)).create();
        verify(storageMock, times(1)).save(genre, "genres.txt");
    }

    @Test
    public void testAdd_GenreIsNull() {
        when(appHelperGenreMock.create()).thenReturn(null);

        boolean result = genreService.add();

        assertFalse(result);
        verify(appHelperGenreMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testAdd_ExceptionThrown() {
        when(appHelperGenreMock.create()).thenThrow(new RuntimeException("Creation failed"));

        boolean result = genreService.add();

        assertFalse(result);
        verify(appHelperGenreMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testPrint() {
        Genre genre1 = new Genre();
        genre1.setGenre("Action");
        Genre genre2 = new Genre();
        genre2.setGenre("Adventure");
        List<Genre> genres = Arrays.asList(genre1, genre2);

        when(storageMock.load("genres.txt")).thenReturn(genres);
        when(appHelperGenreMock.printList(genres)).thenReturn(true);

        boolean result = genreService.print();

        assertTrue(result);
        verify(storageMock, times(1)).load("genres.txt");
        verify(appHelperGenreMock, times(1)).printList(genres);
    }

    @Test
    public void testList() {
        Genre genre1 = new Genre();
        genre1.setGenre("Action");
        Genre genre2 = new Genre();
        genre2.setGenre("Adventure");
        List<Genre> genres = Arrays.asList(genre1, genre2);

        when(storageMock.load("genres.txt")).thenReturn(genres);

        List<Genre> result = genreService.list();

        assertEquals(2, result.size());
        assertEquals("Action", result.get(0).getGenre());
        assertEquals("Adventure", result.get(1).getGenre());
        verify(storageMock, times(1)).load("genres.txt");
    }
}