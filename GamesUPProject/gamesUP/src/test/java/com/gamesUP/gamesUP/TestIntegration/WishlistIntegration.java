package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.Repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class WishlistIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WishlistRepository wishlistRepository;

    private Wishlist testWishlist;

    @BeforeEach
    public void setUp() {
        // Créer une wishlist de test
        testWishlist = new Wishlist();
        testWishlist.setUser(null/* Remplacer par un utilisateur valide */);
        testWishlist.setGames(null/* Remplacer par une liste de jeux */);
        wishlistRepository.save(testWishlist);
    }

    // Test pour récupérer toutes les wishlists (seulement ADMIN)
    @Test
    @WithMockUser(roles = "ADMIN")
    public void getAllWishlistsAsAdmin() throws Exception {
        mockMvc.perform(get("/api/wishlists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(testWishlist.getId()));
    }

    // Test pour récupérer une wishlist par ID
    @Test
    @WithMockUser(roles = "ADMIN")
    public void getWishlistByIdAsAdmin() throws Exception {
        mockMvc.perform(get("/api/wishlists/{id}", testWishlist.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testWishlist.getId()));
    }

    // Test pour récupérer une wishlist par ID avec un utilisateur non autorisé (Doit échouer)
    @Test
    @WithMockUser(roles = "USER")
    public void getWishlistByIdAsUserShouldFail() throws Exception {
        mockMvc.perform(get("/api/wishlists/{id}", testWishlist.getId()))
                .andExpect(status().isForbidden());
    }

    // Test pour ajouter une wishlist
    @Test
    @WithMockUser(roles = "CLIENT")
    public void addWishlistAsClient() throws Exception {
        mockMvc.perform(post("/api/wishlists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"user\": {\"id\": 1}, \"games\": [{\"id\": 1}] }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    // Test pour mettre à jour une wishlist
    @Test
    @WithMockUser(roles = "CLIENT")
    public void updateWishlistAsClient() throws Exception {
        mockMvc.perform(put("/api/wishlists/{id}", testWishlist.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"user\": {\"id\": 1}, \"games\": [{\"id\": 2}] }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testWishlist.getId()));
    }

    // Test pour supprimer une wishlist
    @Test
    @WithMockUser(roles = "CLIENT")
    public void deleteWishlistAsClient() throws Exception {
        mockMvc.perform(delete("/api/wishlists/{id}", testWishlist.getId()))
                .andExpect(status().isNoContent());

        // Vérifier si la wishlist a bien été supprimée de la base
        mockMvc.perform(get("/api/wishlists/{id}", testWishlist.getId()))
                .andExpect(status().isNotFound());
    }
}
