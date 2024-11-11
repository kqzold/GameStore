package services;

import com.example.model.User;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.services.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private AppHelper<User> appHelperUserMock;

    @Mock
    private FileRepository<User> storageMock;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(appHelperUserMock, storageMock);
    }

    @Test
    public void testAdd_Success() {
        User user = new User();
        when(appHelperUserMock.create()).thenReturn(user);
        doNothing().when(storageMock).save(user, "users.txt");

        boolean result = userService.add();

        assertTrue(result);
        verify(appHelperUserMock, times(1)).create();
        verify(storageMock, times(1)).save(user, "users.txt");
    }

    @Test
    public void testAdd_UserIsNull() {
        when(appHelperUserMock.create()).thenReturn(null);

        boolean result = userService.add();

        assertFalse(result);
        verify(appHelperUserMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testAdd_ExceptionThrown() {
        when(appHelperUserMock.create()).thenThrow(new RuntimeException("Creation failed"));

        boolean result = userService.add();

        assertFalse(result);
        verify(appHelperUserMock, times(1)).create();
        verifyNoInteractions(storageMock);
    }

    @Test
    public void testPrint() {
        User user1 = new User();
        user1.setNickname("JohnDoe");
        user1.setPassword("password123");
        user1.setEmail("john@example.com");

        User user2 = new User();
        user2.setNickname("JaneDoe");
        user2.setPassword("password456");
        user2.setEmail("jane@example.com");

        List<User> users = Arrays.asList(user1, user2);

        when(storageMock.load("users.txt")).thenReturn(users);
        when(appHelperUserMock.printList(users)).thenReturn(true);

        boolean result = userService.print();

        assertTrue(result);
        verify(storageMock, times(1)).load("users.txt");
        verify(appHelperUserMock, times(1)).printList(users);
    }

    @Test
    public void testList() {
        User user1 = new User();
        user1.setNickname("JohnDoe");
        User user2 = new User();
        user2.setNickname("JaneDoe");
        List<User> users = Arrays.asList(user1, user2);

        when(storageMock.load("users.txt")).thenReturn(users);

        List<User> result = userService.list();

        assertEquals(2, result.size());
        assertEquals("JohnDoe", result.get(0).getNickname());
        assertEquals("JaneDoe", result.get(1).getNickname());
        verify(storageMock, times(1)).load("users.txt");
    }
}