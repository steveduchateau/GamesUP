package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.Repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;  // Ajouté pour résoudre le problème
import org.mockito.MockitoAnnotations;  // Ajouté pour initialiser les mocks

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private WishlistService wishlistService;

    private Wishlist wishlist;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks
        MockitoAnnotations.openMocks(this);
        
        wishlist = new Wishlist();
        wishlist.setId(1L);
        wishlist.setUser(null); // Simuler l'absence d'utilisateur
    }

    @Test
    void testGetAllWishlists() {
        when(wishlistRepository.findAll()).thenReturn(List.of(wishlist));

        var response = wishlistService.getAllWishlists();

        assertEquals(1, response.size());
    }

    @Test
    void testGetWishlistByIdFound() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));

        Optional<Wishlist> response = wishlistService.getWishlistById(1L);

        assertEquals(wishlist, response.orElse(null));
    }

    @Test
    void testGetWishlistByIdNotFound() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Wishlist> response = wishlistService.getWishlistById(1L);

        assertEquals(Optional.empty(), response);
    }

    @Test
    void testAddWishlist() {
        when(wishlistRepository.save(wishlist)).thenReturn(wishlist);

        Wishlist response = wishlistService.addWishlist(wishlist);

        assertEquals(wishlist, response);
    }

    @Test
    void testUpdateWishlistFound() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));
        when(wishlistRepository.save(wishlist)).thenReturn(wishlist);

        Wishlist response = wishlistService.updateWishlist(1L, wishlist);

        assertEquals(wishlist, response);
    }

    @Test
    void testUpdateWishlistNotFound() {
        when(wishlistRepository.findById(1L)).thenReturn(Optional.empty());

        Wishlist response = wishlistService.updateWishlist(1L, wishlist);

        assertEquals(null, response);
    }

    @Test
    void testDeleteWishlist() {
        Mockito.doNothing().when(wishlistRepository).deleteById(1L);

        wishlistService.deleteWishlist(1L);

        Mockito.verify(wishlistRepository).deleteById(1L);
    }
}
