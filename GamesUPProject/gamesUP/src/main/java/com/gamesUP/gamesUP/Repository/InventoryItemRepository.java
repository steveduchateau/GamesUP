package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    // Vous pouvez ajouter des méthodes spécifiques pour la recherche si nécessaire.
}
