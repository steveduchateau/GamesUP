package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    // Vous pouvez ajouter des méthodes spécifiques pour la recherche si nécessaire.
}
