package services;

import com.example.model.Studio;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.services.StudioService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StudioServiceTest {

    @Mock
    private AppHelper<Studio> appHelperStudioMock;

    @Mock
    private FileRepository<Studio> storageMock;

    private StudioService studioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        studioService = new StudioService(appHelperStudioMock, storageMock);
    }

    @Test
    public void testAdd_Success() {
        Studio studio = new Studio();
        when(appHelperStudioMock.create()).thenReturn(studio);
        doNothing().when(storageMock).save(studio, "studios.txt");

        boolean result = studioService.add();

        assertTrue(result);
        verify(appHelperStudioMock, times(1)).create();
        verify(storageMock, times(1)).save(studio, "studios.txt");
    }

    @Test
    public void testAdd_StudioIsNull() {
        when(appHelperStudioMock.create()).thenReturn(null);

        boolean result = studioService.add();

        assertFalse(result);
        verify(appHelperStudioMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testAdd_ExceptionThrown() {
        when(appHelperStudioMock.create()).thenThrow(new RuntimeException("Creation failed"));

        boolean result = studioService.add();

        assertFalse(result);
        verify(appHelperStudioMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testPrint() {
        Studio studio1 = new Studio();
        studio1.setName("Epic Games");
        Studio studio2 = new Studio();
        studio2.setName("Ubisoft");
        List<Studio> studios = Arrays.asList(studio1, studio2);

        when(storageMock.load("studios.txt")).thenReturn(studios);
        when(appHelperStudioMock.printList(studios)).thenReturn(true);

        boolean result = studioService.print();

        assertTrue(result);
        verify(storageMock, times(1)).load("studios.txt");
        verify(appHelperStudioMock, times(1)).printList(studios);
    }

    @Test
    public void testList() {
        Studio studio1 = new Studio();
        studio1.setName("Epic Games");
        Studio studio2 = new Studio();
        studio2.setName("Ubisoft");
        List<Studio> studios = Arrays.asList(studio1, studio2);

        when(storageMock.load("studios.txt")).thenReturn(studios);

        List<Studio> result = studioService.list();

        assertEquals(2, result.size());
        assertEquals("Epic Games", result.get(0).getName());
        assertEquals("Ubisoft", result.get(1).getName());
        verify(storageMock, times(1)).load("studios.txt");
    }
}