package apphelpers;

import com.example.model.User;
import com.example.model.Purchase;
import com.example.model.Game;
import com.example.apphelpers.AppHelperPurchase;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppHelperPurchaseTest {
    private AppHelperPurchase appHelperPurchase;
    private FileRepository<Purchase> mockPurchaseRepository;
    private FileRepository<User> mockUserRepository;
    private FileRepository<Game> mockGameRepository;
    private Input mockInput;

    @BeforeEach
    public void setUp() {
        mockPurchaseRepository = mock(FileRepository.class);
        mockUserRepository = mock(FileRepository.class);
        mockGameRepository = mock(FileRepository.class);
        mockInput = mock(Input.class);

        appHelperPurchase = new AppHelperPurchase(
                mockPurchaseRepository,
                mockUserRepository,
                mockGameRepository,
                mockInput
        );
    }

    @Test
    public void testCreatePurchaseSuccess() {
        // Mock users
        User user1 = new User("User1", "Email1", "Password1");
        User user2 = new User("User1", "Email1", "Password1");
        List<User> userList = Arrays.asList(user1, user2);
        when(mockUserRepository.load()).thenReturn(userList);

        // Mock games
        Game game1 = new Game();
        game1.setName("Game1");
        game1.setPrice(29.99);
        Game game2 = new Game();
        game2.setName("Game2");
        game2.setPrice(19.99);
        List<Game> gameList = Arrays.asList(game1, game2);
        when(mockGameRepository.load()).thenReturn(gameList);

        // Mock input
        when(mockInput.getInput())
                .thenReturn("1")             // User selection
                .thenReturn("2")             // Game selection
                .thenReturn("2023-10-01");   // Purchase date

        Purchase purchase = appHelperPurchase.create();

        assertNotNull(purchase);
        assertEquals(user1, purchase.getUser());
        assertEquals(game2, purchase.getGame());
        assertEquals(LocalDate.of(2023, 10, 1), purchase.getPurchaseDate());
    }

    @Test
    public void testCreatePurchaseEmptyUserList() {
        when(mockUserRepository.load()).thenReturn(Arrays.asList());

        Purchase purchase = appHelperPurchase.create();

        assertNull(purchase);
    }

    @Test
    public void testCreatePurchaseInvalidUserSelection() {
        // Mock users
        User user1 = new User("User1", "Email1", "Password1");
        List<User> userList = Arrays.asList(user1);
        when(mockUserRepository.load()).thenReturn(userList);

        // Mock input
        when(mockInput.getInput()).thenReturn("5"); // Invalid user index

        Purchase purchase = appHelperPurchase.create();

        assertNull(purchase);
    }

    @Test
    public void testCreatePurchaseEmptyGameList() {
        // Mock users
        User user1 = new User("User1", "Email1", "Password1");
        List<User> userList = Arrays.asList(user1);
        when(mockUserRepository.load()).thenReturn(userList);

        // Mock input
        when(mockInput.getInput()).thenReturn("1"); // User selection

        // Mock games
        when(mockGameRepository.load()).thenReturn(Arrays.asList());

        Purchase purchase = appHelperPurchase.create();

        assertNull(purchase);
    }

    @Test
    public void testCreatePurchaseInvalidGameSelection() {
        // Mock users
        User user1 = new User("User1", "Email1", "Password1");
        List<User> userList = Arrays.asList(user1);
        when(mockUserRepository.load()).thenReturn(userList);

        // Mock games
        Game game1 = new Game();
        game1.setName("Game1");
        game1.setPrice(29.99);
        List<Game> gameList = Arrays.asList(game1);
        when(mockGameRepository.load()).thenReturn(gameList);

        // Mock input
        when(mockInput.getInput())
                .thenReturn("1")     // User selection
                .thenReturn("5");    // Invalid game selection

        Purchase purchase = appHelperPurchase.create();

        assertNull(purchase);
    }

    @Test
    public void testCreatePurchaseInvalidDate() {
        // Mock users
        User user1 = new User("User1", "Email1", "Password1");
        List<User> userList = Arrays.asList(user1);
        when(mockUserRepository.load()).thenReturn(userList);

        // Mock games
        Game game1 = new Game();
        game1.setName("Game1");
        game1.setPrice(29.99);
        List<Game> gameList = Arrays.asList(game1);
        when(mockGameRepository.load()).thenReturn(gameList);

        // Mock input
        when(mockInput.getInput())
                .thenReturn("1")             // User selection
                .thenReturn("1")             // Game selection
                .thenReturn("invalid-date"); // Invalid date

        Purchase purchase = appHelperPurchase.create();

        assertNull(purchase);
    }

    @Test
    public void testCreatePurchaseExceptionHandling() {
        // Mock exception when loading users
        when(mockUserRepository.load()).thenThrow(new RuntimeException("Database error"));

        Purchase purchase = appHelperPurchase.create();

        assertNull(purchase);
    }

    @Test
    public void testPrintList() {
        // Create sample purchases
        User user1 = new User("User1", "Email1", "Password1");
        Game game1 = new Game();
        game1.setName("Game1");
        game1.setPrice(29.99);
        Purchase purchase1 = new Purchase(user1, game1, LocalDate.of(2023, 10, 1));

        User user2 = new User("User1", "Email1", "Password1");
        Game game2 = new Game();
        game2.setName("Game2");
        game2.setPrice(19.99);
        Purchase purchase2 = new Purchase(user2, game2, LocalDate.of(2023, 9, 15));

        List<Purchase> purchaseList = Arrays.asList(purchase1, purchase2);

        // Capture System.out output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        appHelperPurchase.printList(purchaseList);

        // Restore original System.out
        System.setOut(originalOut);

        String output = outputStream.toString();

        // Verify the output contains expected contents
        assertTrue(output.contains("---------- Список покупок --------"));
        assertTrue(output.contains(purchase1.toString()));
        assertTrue(output.contains(purchase2.toString()));
    }

    @Test
    public void testGetRepository() {
        assertEquals(mockPurchaseRepository, appHelperPurchase.getRepository());
    }
}