package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.model.InventoryItem;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory inventory;
    private Game game;

    @BeforeEach
    void setUp() {
        // Initialisation des objets avant chaque test
        inventory = new Inventory();
        game = new Game();
        game.setId(1L);
    }

    @Test
    void testAddStockWhenGameIsNotInInventory() {
        // Ajout d'un stock pour un jeu qui n'est pas encore dans l'inventaire
        inventory.addStock(game, 5);

        // Vérification du stock
        InventoryItem item = inventory.getItems().stream()
                .filter(i -> i.getGame().equals(game))
                .findFirst()
                .orElse(null);

        assertNotNull(item, "Item should be added to inventory");
        assertEquals(5, item.getQuantity(), "Stock should be 5");
    }

    @Test
    void testAddStockWhenGameIsAlreadyInInventory() {
        // Ajout du stock initial
        inventory.addStock(game, 5);

        // Ajout d'un stock supplémentaire
        inventory.addStock(game, 3);

        // Vérification que la quantité est correctement mise à jour
        InventoryItem item = inventory.getItems().stream()
                .filter(i -> i.getGame().equals(game))
                .findFirst()
                .orElse(null);

        assertNotNull(item, "Item should exist in inventory");
        assertEquals(8, item.getQuantity(), "Stock should be 8");
    }

    @Test
    void testRemoveStockWhenStockIsSufficient() {
        // Ajout de 5 unités du jeu dans l'inventaire
        inventory.addStock(game, 5);

        // Retrait de 3 unités
        inventory.removeStock(game, 3);

        // Vérification que la quantité restante est correcte
        InventoryItem item = inventory.getItems().stream()
                .filter(i -> i.getGame().equals(game))
                .findFirst()
                .orElse(null);

        assertNotNull(item, "Item should exist in inventory");
        assertEquals(2, item.getQuantity(), "Stock should be 2 after removal");
    }

    @Test
    void testRemoveStockWhenStockIsInsufficient() {
        // Ajout de 5 unités du jeu
        inventory.addStock(game, 5);

        // Tentative de retrait de 10 unités (plus que le stock disponible)
        inventory.removeStock(game, 10);

        // Vérification que la quantité ne devient pas négative
        InventoryItem item = inventory.getItems().stream()
                .filter(i -> i.getGame().equals(game))
                .findFirst()
                .orElse(null);

        assertNotNull(item, "Item should exist in inventory");
        assertEquals(0, item.getQuantity(), "Stock should be 0 after insufficient removal");
    }

    @Test
    void testRemoveStockWhenGameDoesNotExistInInventory() {
        // Tentative de retrait d'un jeu qui n'est pas dans l'inventaire
        inventory.removeStock(game, 3);

        // Vérification que l'inventaire est toujours vide
        InventoryItem item = inventory.getItems().stream()
                .filter(i -> i.getGame().equals(game))
                .findFirst()
                .orElse(null);

        assertNull(item, "Item should not exist in inventory");
    }
}
