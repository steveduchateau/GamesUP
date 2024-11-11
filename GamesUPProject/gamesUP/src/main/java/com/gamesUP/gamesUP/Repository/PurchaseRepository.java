package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    // Méthode pour récupérer les achats par userId
    List<Purchase> findByUserId(Long userId);  // Changement de String à Long pour correspondre au type de 'userId'
}
