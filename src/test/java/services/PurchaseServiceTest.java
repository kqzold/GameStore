package services;

import com.example.model.Purchase;
import com.example.services.PurchaseService;
import com.example.interfaces.AppHelper;
import com.example.interfaces.FileRepository;
import com.example.interfaces.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseServiceTest {

    private List<Purchase> purchases;
    private AppHelper<Purchase> appHelperPurchase;
    private Input inputProvider;
    private FileRepository<Purchase> repository;
    private PurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        purchases = new ArrayList<>();
        appHelperPurchase = mock(AppHelper.class);
        inputProvider = mock(Input.class);
        repository = mock(FileRepository.class);
        when(appHelperPurchase.getRepository()).thenReturn(repository);
        purchaseService = new PurchaseService(purchases, appHelperPurchase, inputProvider);
    }

    @Test
    void remove_Successful() {
        Purchase purchase = new Purchase();
        purchases.add(purchase);
        when(inputProvider.getInput()).thenReturn("1");

        boolean result = purchaseService.remove(purchase);

        assertTrue(result);
        assertTrue(purchases.isEmpty());
        verify(repository).save(purchases);
    }

    @Test
    void remove_InvalidIndex() {
        Purchase purchase = new Purchase();
        purchases.add(purchase);
        when(inputProvider.getInput()).thenReturn("2");

        boolean result = purchaseService.remove(purchase);

        assertFalse(result);
        assertEquals(1, purchases.size());
        verify(repository, never()).save(anyList());
    }

    @Test
    void remove_InvalidInput() {
        Purchase purchase = new Purchase();
        purchases.add(purchase);
        when(inputProvider.getInput()).thenReturn("abc");

        boolean result = purchaseService.remove(purchase);

        assertFalse(result);
        assertEquals(1, purchases.size());
        verify(repository, never()).save(any(List.class));
    }
}