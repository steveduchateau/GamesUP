package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.Repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    private Purchase purchase;

    @BeforeEach
    public void setUp() {
        purchase = new Purchase();
        purchase.setId(1L);
        purchase.setPaid(true);
        purchase.setDelivered(true);
        purchase.setArchived(false);
    }

    @Test
    public void testGetAllPurchases() {
        when(purchaseRepository.findAll()).thenReturn(Collections.singletonList(purchase));
        var purchases = purchaseService.getAllPurchases();
        assertFalse(purchases.isEmpty());
        verify(purchaseRepository).findAll();
    }

    @Test
    public void testGetPurchaseById_Success() {
        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));
        Optional<Purchase> result = purchaseService.getPurchaseById(1L);
        assertTrue(result.isPresent());
        assertEquals(purchase, result.get());
    }

    @Test
    public void testGetPurchaseById_NotFound() {
        when(purchaseRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Purchase> result = purchaseService.getPurchaseById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testAddPurchase() {
        when(purchaseRepository.save(purchase)).thenReturn(purchase);
        Purchase result = purchaseService.addPurchase(purchase);
        assertEquals(purchase, result);
    }

    @Test
    public void testUpdatePurchase_Success() {
        when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));
        when(purchaseRepository.save(purchase)).thenReturn(purchase);
        Purchase updatedPurchase = purchaseService.updatePurchase(1L, purchase);
        assertNotNull(updatedPurchase);
        assertEquals(purchase, updatedPurchase);
    }

    @Test
    public void testUpdatePurchase_NotFound() {
        when(purchaseRepository.findById(1L)).thenReturn(Optional.empty());
        Purchase updatedPurchase = purchaseService.updatePurchase(1L, purchase);
        assertNull(updatedPurchase);
    }

    @Test
    public void testDeletePurchase() {
        doNothing().when(purchaseRepository).deleteById(1L);
        purchaseService.deletePurchase(1L);
        verify(purchaseRepository).deleteById(1L);
    }
}
