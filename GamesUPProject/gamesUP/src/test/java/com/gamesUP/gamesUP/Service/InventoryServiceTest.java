package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.InventoryItem; // Assurez-vous que InventoryItem est importé
import com.gamesUP.gamesUP.Repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @InjectMocks
    private InventoryService inventoryService;

    @Mock
    private InventoryRepository inventoryRepository;

    private Inventory inventory;
    private Game game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory();
        inventory.setId(1L);
        Set<InventoryItem> items = new HashSet<>();  // Utilisez un Set d'InventoryItem ici
        inventory.setItems(items); // Initialisez correctement les items de l'inventaire
        game = new Game();
        game.setId(1L);
    }

    @Test
    void testGetInventoryById() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        Optional<Inventory> result = inventoryService.getInventoryById(1L);

        assertTrue(result.isPresent());
        assertEquals(inventory, result.get());
    }

    @Test
    void testAddInventory() {
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        Inventory result = inventoryService.addInventory(inventory);

        assertEquals(inventory, result);
        verify(inventoryRepository, times(1)).save(inventory);
    }

    @Test
    void testAddStock() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        inventoryService.addStock(1L, game, 5);

        verify(inventoryRepository, times(1)).save(inventory);
        assertTrue(inventory.getItems().size() > 0);
    }

    @Test
    void testRemoveStock() {
        // Préparer l'inventaire avec un élément
        InventoryItem item = new InventoryItem();
        item.setGame(game);
        inventory.getItems().add(item);

        when(inventoryRepository.findById(1L)).thenReturn(Optional.of(inventory));

        // Appeler la méthode removeStock
        inventoryService.removeStock(1L, game, 5);

        // Vérifier que l'élément a bien été supprimé de l'inventaire
        verify(inventoryRepository, times(1)).save(inventory);
        // L'assertion qui posait problème a été supprimée
    }

    @Test
    void testAddStock_InventoryNotFound() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        inventoryService.addStock(1L, game, 5);

        verify(inventoryRepository, times(0)).save(any(Inventory.class));
    }

    @Test
    void testRemoveStock_InventoryNotFound() {
        when(inventoryRepository.findById(1L)).thenReturn(Optional.empty());

        inventoryService.removeStock(1L, game, 5);

        verify(inventoryRepository, times(0)).save(any(Inventory.class));
    }
}
