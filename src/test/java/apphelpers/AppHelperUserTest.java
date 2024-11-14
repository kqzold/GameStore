package apphelpers;

import com.example.model.User;
import com.example.apphelpers.AppHelperUser;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppHelperUserTest {
    private AppHelperUser appHelperUser;
    private FileRepository<User> mockUserRepository;
    private Input mockInput;

    @BeforeEach
    public void setUp() {
        mockUserRepository = mock(FileRepository.class);
        mockInput = mock(Input.class);
        appHelperUser = new AppHelperUser(mockUserRepository, mockInput);
    }

    @Test
    public void testCreateUserSuccess() {
        when(mockInput.getInput())
                .thenReturn("JohnDoe")          // Nickname input
                .thenReturn("password123")      // Password input
                .thenReturn("john@example.com"); // Email input

        User user = appHelperUser.create();

        assertNotNull(user);
        assertEquals("JohnDoe", user.getNickname());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testCreateUserExceptionHandling() {
        when(mockInput.getInput()).thenThrow(new RuntimeException("Input error"));

        // Capture System.out output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        User user = appHelperUser.create();

        // Restore original System.out
        System.setOut(originalOut);

        assertNull(user);
        String output = outputStream.toString();
        assertTrue(output.contains("Ошибка при создании пользователя"));
    }

    @Test
    public void testPrintList() {
        User user1 = new User("JohnDoe", "password123", "john@example.com");
        User user2 = new User("JaneSmith", "pass456", "jane@example.com");
        List<User> users = Arrays.asList(user1, user2);

    // Capture System.out output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        appHelperUser.printList(users);

    // Restore original System.out
        System.setOut(originalOut);

        String output = outputStream.toString();

    // Print the captured output for debugging
        System.out.println("Captured Output:\n" + output);

    // Your existing assertions...
}

    @Test
    public void testGetRepository() {
        assertEquals(mockUserRepository, appHelperUser.getRepository());
    }
}