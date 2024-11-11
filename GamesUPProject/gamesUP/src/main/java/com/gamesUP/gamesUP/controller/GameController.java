package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // Accès à tous les jeux pour les clients et admins
    @GetMapping
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public List<Game> getAllJeux() {
        return gameService.getAllGames();
    }

    // Recherche de jeux (par nom ou par catégorie)
    @GetMapping("/search")
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    public List<Game> searchGames(@RequestParam String query) {
        return gameService.searchGames(query);
    }

    // Ajouter un jeu : réservé aux administrateurs
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> ajouterJeu(@RequestBody Game game) {
        if (game == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le jeu est invalide.");
        }
        gameService.saveGame(game);
        return ResponseEntity.ok("Jeu ajouté avec succès.");
    }

    // Mettre à jour un jeu : réservé aux administrateurs
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateGame(@PathVariable Long id, @RequestBody Game game) {
        if (gameService.getGameById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le jeu avec l'ID " + id + " n'a pas été trouvé.");
        }
        gameService.updateGame(id, game);
        return ResponseEntity.ok("Jeu mis à jour avec succès.");
    }
}
