package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
    // Vous pouvez ajouter des méthodes de recherche spécifiques si nécessaire.
}
