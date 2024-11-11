package apphelpers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.List;
import java.util.ArrayList;

import com.example.apphelpers.StudioAppHelper;
import com.example.model.Studio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;



public class StudioAppHelperTest {

    private StudioAppHelper studioAppHelper;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        studioAppHelper = new StudioAppHelper();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCreate() {
        StudioAppHelper spyHelper = Mockito.spy(studioAppHelper);
        doReturn("Studio Name").when(spyHelper).getString();

        Studio studio = spyHelper.create();

        assertNotNull(studio);
        assertEquals("Studio Name", studio.getName());
    }

    @Test
    public void testPrintList() {
        List<Studio> studios = new ArrayList<>();
        studios.add(new Studio("Studio 1"));
        studios.add(new Studio("Studio 2"));

        studioAppHelper.printList(studios);

        String expectedOutput1 = "1. Studio 1\n";
        String expectedOutput2 = "2. Studio 2\n";

        assertTrue(outContent.toString().contains(expectedOutput1));
        assertTrue(outContent.toString().contains(expectedOutput2));
    }

    @Test
    public void testCreateHandleError() {
        StudioAppHelper spyHelper = Mockito.spy(studioAppHelper);
        doThrow(new RuntimeException("Error")).when(spyHelper).getString();

        Studio studio = spyHelper.create();

        assertNull(studio);
        assertTrue(outContent.toString().contains("Error"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        outContent.reset();
    }

}
