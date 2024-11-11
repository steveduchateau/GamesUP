package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void saveGame(Game game) {
        if (game != null) {
            gameRepository.save(game);
        }
    }

    public Game getGameById(Long id) {  // Assurez-vous que l'ID est de type Long
        Optional<Game> game = gameRepository.findById(id);
        return game.orElse(null);  // Si le jeu n'est pas trouvé, retourne null
    }

    public void updateGame(Long id, Game game) {
        if (gameRepository.existsById(id)) {
            game.setId(id);  // Assure-toi que l'ID du jeu reste le même
            gameRepository.save(game);
        }
    }

    public List<Game> searchGames(String query) {
        return gameRepository.findByTitleContainingIgnoreCase(query);  // Recherche par titre
    }
}
