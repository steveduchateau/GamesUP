package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // Vous pouvez ajouter des méthodes spécifiques pour la recherche si nécessaire.
}
