package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.Service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseService purchaseService;

    private Purchase purchase;

    @BeforeEach
    public void setup() {
        // Initialiser un achat fictif pour le test
        purchase = new Purchase();
        purchase.setDate(new Date());
        purchase.setPaid(true);
        purchase.setDelivered(true);
        purchase.setArchived(false);
        purchase.setUserId(1L); // L'ID de l'utilisateur peut être défini comme souhaité.
    }

    @Test
    public void testAddPurchase() throws Exception {
        // Test pour ajouter un achat (POST)
        mockMvc.perform(post("/api/purchases")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"line\":[], \"date\":\"2024-11-10T12:00:00\", \"paid\": true, \"delivered\": true, \"archived\": false, \"userId\": 1}"))
                .andExpect(status().isCreated()) // Vérifier que le statut HTTP 201 (créé) est renvoyé
                .andExpect(jsonPath("$.paid").value(true)) // Vérifier que la valeur 'paid' est correcte
                .andExpect(jsonPath("$.delivered").value(true)); // Vérifier que la valeur 'delivered' est correcte
    }

    @Test
    public void testGetAllPurchases() throws Exception {
        // Ajouter un achat pour le test
        purchaseService.addPurchase(purchase);

        // Test pour récupérer tous les achats (GET)
        mockMvc.perform(get("/api/purchases")
                .header("Authorization", "Bearer <token>")) // Remplacez <token> par un token valide si nécessaire
                .andExpect(status().isOk()) // Vérifier que le statut HTTP 200 est renvoyé
                .andExpect(jsonPath("$[0].userId").value(1)); // Vérifier que l'ID de l'utilisateur est correct
    }

    @Test
    public void testGetPurchaseById() throws Exception {
        // Ajouter un achat pour le test
        purchaseService.addPurchase(purchase);

        // Test pour récupérer un achat par son ID (GET)
        mockMvc.perform(get("/api/purchases/{id}", purchase.getId())
                .header("Authorization", "Bearer <token>")) // Remplacez <token> par un token valide si nécessaire
                .andExpect(status().isOk()) // Vérifier que le statut HTTP 200 est renvoyé
                .andExpect(jsonPath("$.id").value(purchase.getId())); // Vérifier que l'ID de l'achat est correct
    }

    @Test
    public void testUpdatePurchase() throws Exception {
        // Ajouter un achat pour le test
        Purchase newPurchase = purchaseService.addPurchase(purchase);
        newPurchase.setPaid(false); // Changer l'état de l'achat pour le test

        // Test pour mettre à jour un achat (PUT)
        mockMvc.perform(put("/api/purchases/{id}", newPurchase.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"line\":[], \"date\":\"2024-11-10T12:00:00\", \"paid\": false, \"delivered\": true, \"archived\": false, \"userId\": 1}"))
                .andExpect(status().isOk()) // Vérifier que le statut HTTP 200 est renvoyé
                .andExpect(jsonPath("$.paid").value(false)); // Vérifier que la valeur 'paid' a été mise à jour
    }

    @Test
    public void testDeletePurchase() throws Exception {
        // Ajouter un achat pour le test
        Purchase newPurchase = purchaseService.addPurchase(purchase);

        // Test pour supprimer un achat (DELETE)
        mockMvc.perform(delete("/api/purchases/{id}", newPurchase.getId())
                .header("Authorization", "Bearer <token>")) // Remplacez <token> par un token valide si nécessaire
                .andExpect(status().isNoContent()); // Vérifier que le statut HTTP 204 (no content) est renvoyé
    }
}
