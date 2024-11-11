package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.UserPurchase;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPurchaseRepository extends JpaRepository<UserPurchase, Integer> {
    // Méthode personnalisée pour trouver les achats d'un utilisateur spécifique
    List<UserPurchase> findByUserId(int userId);
}
