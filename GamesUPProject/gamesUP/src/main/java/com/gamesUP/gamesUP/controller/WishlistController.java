package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    // Endpoint pour récupérer toutes les wishlists (seulement pour ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Wishlist> getAllWishlists() {
        return wishlistService.getAllWishlists();
    }

    // Endpoint pour récupérer une wishlist par son ID (accessible à ADMIN ou à l'utilisateur concerné)
    @PreAuthorize("hasRole('ADMIN') or @wishlistService.isOwnerOfWishlist(#id, authentication.name)") 
    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistById(id);
        return wishlist.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour récupérer une wishlist par l'ID de l'utilisateur (accessible seulement à l'utilisateur ou à un ADMIN)
    @PreAuthorize("hasRole('ADMIN') or @wishlistService.isOwnerOfWishlistByUserId(#userId, authentication.name)")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Wishlist> getWishlistByUserId(@PathVariable Long userId) {
        Optional<Wishlist> wishlist = wishlistService.getWishlistByUserId(userId);
        return wishlist.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter une nouvelle wishlist (accessible seulement à l'utilisateur connecté ou ADMIN)
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Wishlist> addWishlist(@RequestBody Wishlist wishlist) {
        Wishlist newWishlist = wishlistService.addWishlist(wishlist);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWishlist);
    }

    // Endpoint pour mettre à jour une wishlist (accessible uniquement à l'utilisateur concerné ou ADMIN)
    @PreAuthorize("hasRole('ADMIN') or @wishlistService.isOwnerOfWishlist(#id, authentication.name)")
    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlistDetails) {
        Wishlist updatedWishlist = wishlistService.updateWishlist(id, wishlistDetails);
        return updatedWishlist != null ? ResponseEntity.ok(updatedWishlist)
                                      : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer une wishlist (accessible uniquement à l'utilisateur concerné ou ADMIN)
    @PreAuthorize("hasRole('ADMIN') or @wishlistService.isOwnerOfWishlist(#id, authentication.name)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }
}
