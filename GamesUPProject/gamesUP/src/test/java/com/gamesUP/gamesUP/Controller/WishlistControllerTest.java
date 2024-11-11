package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.Service.WishlistService;
import com.gamesUP.gamesUP.controller.WishlistController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WishlistControllerTest {

    @Mock
    private WishlistService wishlistService;

    @InjectMocks
    private WishlistController wishlistController;

    private Wishlist wishlist;

    @BeforeEach
    void setUp() {
        // Initialise les mocks avant chaque test
        MockitoAnnotations.openMocks(this);

        wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setUser(null); // Simuler l'absence d'utilisateur
    }

    @Test
    void testGetAllWishlists() {
        // Simule le comportement du service
        when(wishlistService.getAllWishlists()).thenReturn(List.of(wishlist));

        // Appelle le contrôleur
        var response = wishlistController.getAllWishlists();

        // Vérifie le résultat
        assertEquals(1, response.size());
    }

    @Test
    void testGetWishlistByIdFound() {
        when(wishlistService.getWishlistById(1L)).thenReturn(Optional.of(wishlist));

        ResponseEntity<Wishlist> response = wishlistController.getWishlistById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishlist, response.getBody());
    }

    @Test
    void testGetWishlistByIdNotFound() {
        when(wishlistService.getWishlistById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Wishlist> response = wishlistController.getWishlistById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddWishlist() {
        when(wishlistService.addWishlist(wishlist)).thenReturn(wishlist);

        ResponseEntity<Wishlist> response = wishlistController.addWishlist(wishlist);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(wishlist, response.getBody());
    }

    @Test
    void testUpdateWishlistFound() {
        when(wishlistService.updateWishlist(1L, wishlist)).thenReturn(wishlist);

        ResponseEntity<Wishlist> response = wishlistController.updateWishlist(1L, wishlist);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wishlist, response.getBody());
    }

    @Test
    void testUpdateWishlistNotFound() {
        when(wishlistService.updateWishlist(1L, wishlist)).thenReturn(null);

        ResponseEntity<Wishlist> response = wishlistController.updateWishlist(1L, wishlist);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteWishlist() {
        Mockito.doNothing().when(wishlistService).deleteWishlist(1L);

        ResponseEntity<Void> response = wishlistController.deleteWishlist(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
