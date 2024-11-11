package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.Service.PurchaseLineService;
import com.gamesUP.gamesUP.controller.PurchaseLineController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PurchaseLineControllerTest {

    @InjectMocks
    private PurchaseLineController purchaseLineController;

    @Mock
    private PurchaseLineService purchaseLineService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseLineController).build();
    }

    @Test
    void testGetAllPurchaseLines() throws Exception {
        PurchaseLine line1 = new PurchaseLine(1, 101, 202, 29.99);
        PurchaseLine line2 = new PurchaseLine(2, 102, 203, 49.99);
        when(purchaseLineService.getAllPurchaseLines()).thenReturn(Arrays.asList(line1, line2));

        mockMvc.perform(get("/api/purchase-lines"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void testGetPurchaseLineById() throws Exception {
        PurchaseLine line = new PurchaseLine(1, 101, 202, 29.99);
        when(purchaseLineService.getPurchaseLineById(1)).thenReturn(Optional.of(line));

        mockMvc.perform(get("/api/purchase-lines/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetPurchaseLineByIdNotFound() throws Exception {
        when(purchaseLineService.getPurchaseLineById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/purchase-lines/999"))
               .andExpect(status().isNotFound());
    }

    @Test
    void testAddPurchaseLine() throws Exception {
        PurchaseLine newLine = new PurchaseLine(3, 103, 204, 19.99);
        when(purchaseLineService.addPurchaseLine(any(PurchaseLine.class))).thenReturn(newLine);

        mockMvc.perform(post("/api/purchase-lines")
                .contentType("application/json")
                .content("{\"utilisateurId\":103,\"jeuId\":204,\"prix\":19.99}"))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void testUpdatePurchaseLine() throws Exception {
        PurchaseLine updatedLine = new PurchaseLine(1, 101, 202, 35.99);
        when(purchaseLineService.updatePurchaseLine(eq(1), any(PurchaseLine.class))).thenReturn(updatedLine);

        mockMvc.perform(put("/api/purchase-lines/1")
                .contentType("application/json")
                .content("{\"utilisateurId\":101,\"jeuId\":202,\"prix\":35.99}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.prix").value(35.99));
    }

    @Test
    void testDeletePurchaseLine() throws Exception {
        doNothing().when(purchaseLineService).deletePurchaseLine(1);

        mockMvc.perform(delete("/api/purchase-lines/1"))
               .andExpect(status().isNoContent());
    }
}
