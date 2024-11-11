package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    // Rechercher une wishlist par utilisateur
    Optional<Wishlist> findByUserId(Long userId);
}
