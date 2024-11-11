package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.Service.InventoryService;
import com.gamesUP.gamesUP.controller.InventoryController;
import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class InventoryControllerTest {

    @InjectMocks
    private InventoryController inventoryController;

    @Mock
    private InventoryService inventoryService;

    @Mock
    private Game game;  // Mock de l'objet Game

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventory = new Inventory();
        inventory.setId(1L);
        
        // Mock des méthodes nécessaires de l'objet Game
        when(game.getId()).thenReturn(1L);
    }

    @Test
    void testAddInventory() {
        when(inventoryService.addInventory(any(Inventory.class))).thenReturn(inventory);

        ResponseEntity<Inventory> response = inventoryController.addInventory(inventory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(inventory, response.getBody());
    }

    @Test
    void testAddStock() {
        // Utilisation d'un matcher personnalisé pour vérifier les arguments
        doNothing().when(inventoryService).addStock(eq(1L), argThat(gameArg -> gameArg.getId().equals(game.getId())), eq(5));

        // Ajout d'un stock de jeu avec l'ID de l'inventaire, l'ID du jeu et la quantité
        ResponseEntity<Void> response = inventoryController.addStock(1L, 1L, 5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Vérification de l'appel avec les bons arguments
        verify(inventoryService, times(1)).addStock(eq(1L), argThat(gameArg -> gameArg.getId().equals(game.getId())), eq(5));
    }

    @Test
    void testRemoveStock() {
        // Utilisation d'un matcher personnalisé pour vérifier les arguments
        doNothing().when(inventoryService).removeStock(eq(1L), argThat(gameArg -> gameArg.getId().equals(game.getId())), eq(5));

        // Suppression d'un stock de jeu avec l'ID de l'inventaire, l'ID du jeu et la quantité
        ResponseEntity<Void> response = inventoryController.removeStock(1L, 1L, 5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Vérification de l'appel avec les bons arguments
        verify(inventoryService, times(1)).removeStock(eq(1L), argThat(gameArg -> gameArg.getId().equals(game.getId())), eq(5));
    }
}
