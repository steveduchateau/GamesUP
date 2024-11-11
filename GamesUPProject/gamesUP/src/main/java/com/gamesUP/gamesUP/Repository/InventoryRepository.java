package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Vous pouvez ajouter des méthodes spécifiques pour la recherche si nécessaire.
}
