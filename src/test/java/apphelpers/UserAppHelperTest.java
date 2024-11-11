package apphelpers;

import com.example.apphelpers.UserAppHelper;
import com.example.model.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

public class UserAppHelperTest {

    private UserAppHelper userAppHelper;

    @BeforeEach
    public void setUp() {
        userAppHelper = new UserAppHelper() {
            private int callCount = 0;
            private String[] inputs = {"JohnDoe", "password123"};

            @Override
            public String getString() {
                return inputs[callCount++];
            }
        };
    }

    @Test
    public void testCreate() {
        User user = userAppHelper.create();
        assertNotNull(user);
        assertEquals("JohnDoe", user.getNickname());
        assertEquals("password123", user.getPassword());
    }

    @Test
    public void testPrintList() {
        User user1 = new User();
        user1.setNickname("JohnDoe");
        user1.setPassword("password123");
        user1.setEmail("john@example.com");

        User user2 = new User();
        user2.setNickname("JaneDoe");
        user2.setPassword("password456");
        user2.setEmail("jane@example.com");

        List<User> users = Arrays.asList(user1, user2);

        boolean result = userAppHelper.printList(users);

        assertFalse(result);
        // Additional assertions can be added here if needed
    }
}