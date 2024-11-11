package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.InventoryItem;
import com.gamesUP.gamesUP.Service.InventoryItemService;
import com.gamesUP.gamesUP.controller.InventoryItemController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class InventoryItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InventoryItemService inventoryItemService;

    @InjectMocks
    private InventoryItemController inventoryItemController;

    @Test
    void testGetInventoryItemById_Success() throws Exception {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(1L);
        inventoryItem.setQuantity(10);

        when(inventoryItemService.getInventoryItemById(anyLong())).thenReturn(Optional.of(inventoryItem));

        mockMvc = MockMvcBuilders.standaloneSetup(inventoryItemController).build();

        mockMvc.perform(get("/api/inventory-items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void testGetInventoryItemById_NotFound() throws Exception {
        when(inventoryItemService.getInventoryItemById(anyLong())).thenReturn(Optional.empty());

        mockMvc = MockMvcBuilders.standaloneSetup(inventoryItemController).build();

        mockMvc.perform(get("/api/inventory-items/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddInventoryItem() throws Exception {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(1L);
        inventoryItem.setQuantity(10);

        // Utilisation de any(InventoryItem.class) pour matcher tous les objets InventoryItem
        when(inventoryItemService.addInventoryItem(any(InventoryItem.class))).thenReturn(inventoryItem);

        mockMvc = MockMvcBuilders.standaloneSetup(inventoryItemController).build();

        mockMvc.perform(post("/api/inventory-items")
                        .contentType("application/json")
                        .content("{\"quantity\": 10}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.quantity").value(10));
    }

    @Test
    void testUpdateQuantity() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryItemController).build();

        mockMvc.perform(put("/api/inventory-items/1?quantity=20"))
                .andExpect(status().isOk());

        // Vérification que la méthode updateQuantity a été appelée avec les bons paramètres
        verify(inventoryItemService).updateQuantity(1L, 20);
    }
}
