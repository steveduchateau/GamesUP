package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.Service.PurchaseLineService;
import com.gamesUP.gamesUP.controller.PurchaseLineController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PurchaseLineControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PurchaseLineService purchaseLineService;

    @InjectMocks
    private PurchaseLineController purchaseLineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(purchaseLineController).build();

        new PurchaseLine(1, 1001, 2001, 29.99);
    }

    @Test
    void testUpdatePurchaseLineNotFound() throws Exception {
        // Simuler le cas où l'ID de la ligne d'achat n'est pas trouvé
        when(purchaseLineService.getPurchaseLineById(1)).thenReturn(java.util.Optional.empty());

        // Effectuer une requête PUT pour une ligne d'achat qui n'existe pas
        mockMvc.perform(put("/api/purchase-lines/1")
                .contentType("application/json")
                .content("{\"id\":1, \"utilisateurId\":1001, \"jeuId\":2001, \"prix\":39.99}"))
                .andExpect(status().isNotFound());  // Vérifier que le statut est 404 (Not Found)
    }
}
