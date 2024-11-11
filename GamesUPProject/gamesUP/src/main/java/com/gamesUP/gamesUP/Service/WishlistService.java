package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    // Méthode pour récupérer toutes les wishlists
    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    // Méthode pour récupérer une wishlist par son ID
    public Optional<Wishlist> getWishlistById(Long id) {
        return wishlistRepository.findById(id);
    }

    // Méthode pour récupérer une wishlist par l'ID de l'utilisateur
    public Optional<Wishlist> getWishlistByUserId(Long userId) {
        return wishlistRepository.findByUserId(userId);
    }

    // Méthode pour ajouter une nouvelle wishlist
    public Wishlist addWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    // Méthode pour mettre à jour une wishlist
    public Wishlist updateWishlist(Long id, Wishlist wishlistDetails) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(id);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            wishlist.setUser(wishlistDetails.getUser());
            wishlist.setGames(wishlistDetails.getGames());
            return wishlistRepository.save(wishlist);
        }
        return null;
    }

    // Méthode pour supprimer une wishlist
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }
}
