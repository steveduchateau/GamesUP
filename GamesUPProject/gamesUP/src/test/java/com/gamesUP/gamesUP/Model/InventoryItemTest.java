package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.InventoryItem;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryItemTest {

    @Test
    void testGettersAndSetters() {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(1L);
        inventoryItem.setQuantity(10);

        assertEquals(1L, inventoryItem.getId());
        assertEquals(10, inventoryItem.getQuantity());
    }
}
