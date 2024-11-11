package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.Service.PurchaseService;
import com.gamesUP.gamesUP.controller.PurchaseController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseControllerTest {

    @Mock
    private PurchaseService purchaseService;

    @InjectMocks
    private PurchaseController purchaseController;

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
        when(purchaseService.getAllPurchases()).thenReturn(Collections.singletonList(purchase));
        var response = purchaseController.getAllPurchases();
        assertFalse(response.isEmpty());
        verify(purchaseService).getAllPurchases();
    }

    @Test
    public void testGetPurchaseById_Success() {
        when(purchaseService.getPurchaseById(1L)).thenReturn(Optional.of(purchase));
        ResponseEntity<Purchase> response = purchaseController.getPurchaseById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(purchase, response.getBody());
    }

    @Test
    public void testGetPurchaseById_NotFound() {
        when(purchaseService.getPurchaseById(1L)).thenReturn(Optional.empty());
        ResponseEntity<Purchase> response = purchaseController.getPurchaseById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddPurchase() {
        when(purchaseService.addPurchase(purchase)).thenReturn(purchase);
        ResponseEntity<Purchase> response = purchaseController.addPurchase(purchase);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(purchase, response.getBody());
    }

    @Test
    public void testUpdatePurchase_Success() {
        when(purchaseService.updatePurchase(1L, purchase)).thenReturn(purchase);
        ResponseEntity<Purchase> response = purchaseController.updatePurchase(1L, purchase);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(purchase, response.getBody());
    }

    @Test
    public void testUpdatePurchase_NotFound() {
        when(purchaseService.updatePurchase(1L, purchase)).thenReturn(null);
        ResponseEntity<Purchase> response = purchaseController.updatePurchase(1L, purchase);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeletePurchase() {
        doNothing().when(purchaseService).deletePurchase(1L);
        ResponseEntity<Void> response = purchaseController.deletePurchase(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(purchaseService).deletePurchase(1L);
    }
}
