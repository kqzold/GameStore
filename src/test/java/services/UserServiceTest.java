package services;

import com.example.model.User;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;
import com.example.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private List<User> users;
    private AppHelper<User> appHelperUser;
    private Input inputProvider;
    private FileRepository<User> userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        appHelperUser = mock(AppHelper.class);
        inputProvider = mock(Input.class);
        userRepository = mock(FileRepository.class);
        userService = new UserService(users, appHelperUser, inputProvider, userRepository);
    }

    @Test
    void testAdd_Success() {
        User user = new User();
        when(appHelperUser.create()).thenReturn(user);

        boolean result = userService.add();

        assertTrue(result);
        assertEquals(1, users.size());
        verify(userRepository, times(1)).save(users);
    }

    @Test
    void testAdd_Failure() {
        when(appHelperUser.create()).thenReturn(null);

        boolean result = userService.add();

        assertFalse(result);
        assertEquals(0, users.size());
        verify(userRepository, never()).save(anyList());
    }
}