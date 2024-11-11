package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    // Recherche insensible à la casse par titre
    List<Game> findByTitleContainingIgnoreCase(String title);
}
