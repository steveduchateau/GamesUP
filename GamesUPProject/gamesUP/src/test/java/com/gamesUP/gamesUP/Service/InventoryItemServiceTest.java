package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.InventoryItem;
import com.gamesUP.gamesUP.Repository.InventoryItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryItemServiceTest {

    @Mock
    private InventoryItemRepository inventoryItemRepository;

    @InjectMocks
    private InventoryItemService inventoryItemService;

    @Test
    void testGetInventoryItemById_Found() {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(1L);
        inventoryItem.setQuantity(10);

        when(inventoryItemRepository.findById(1L)).thenReturn(Optional.of(inventoryItem));

        Optional<InventoryItem> result = inventoryItemService.getInventoryItemById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals(10, result.get().getQuantity());
    }

    @Test
    void testGetInventoryItemById_NotFound() {
        when(inventoryItemRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<InventoryItem> result = inventoryItemService.getInventoryItemById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testAddInventoryItem() {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setQuantity(10);

        when(inventoryItemRepository.save(inventoryItem)).thenReturn(inventoryItem);

        InventoryItem result = inventoryItemService.addInventoryItem(inventoryItem);

        assertNotNull(result);
        assertEquals(10, result.getQuantity());
        verify(inventoryItemRepository, times(1)).save(inventoryItem);
    }

    @Test
    void testUpdateQuantity() {
        Long itemId = 1L;
        int newQuantity = 20;

        // Simuler la récupération de l'élément d'inventaire
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(itemId);
        inventoryItem.setQuantity(10);

        when(inventoryItemRepository.findById(itemId)).thenReturn(Optional.of(inventoryItem));

        inventoryItemService.updateQuantity(itemId, newQuantity);

        assertEquals(newQuantity, inventoryItem.getQuantity());
        verify(inventoryItemRepository, times(1)).save(inventoryItem);
    }
}
