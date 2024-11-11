package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.PurchaseLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLineRepository extends JpaRepository<PurchaseLine, Integer> {
    // Vous pouvez ajouter des méthodes spécifiques pour la recherche si nécessaire.
}
