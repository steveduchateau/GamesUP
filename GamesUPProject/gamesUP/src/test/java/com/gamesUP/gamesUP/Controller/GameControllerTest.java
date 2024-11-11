package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.Service.GameService;
import com.gamesUP.gamesUP.controller.GameController;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameService;

    private Game game1;
    private Game game2;
    private Author author;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        author = new Author(); // Auteur pour les jeux
        game1 = new Game("Game 1", author);
        game2 = new Game("Game 2", author);
    }

    @Test
    void testGetAllJeux() {
        // Liste de jeux à retourner
        List<Game> games = Arrays.asList(game1, game2);

        // Simulation de la méthode gameService.getAllGames()
        when(gameService.getAllGames()).thenReturn(games);

        // Appel de la méthode du contrôleur
        List<Game> result = gameController.getAllJeux();

        // Vérifications
        assertEquals(2, result.size());
        assertEquals("Game 1", result.get(0).getTitle());
        assertEquals("Game 2", result.get(1).getTitle());

        // Vérifie que gameService.getAllGames() a été appelée une fois
        verify(gameService, times(1)).getAllGames();
    }

    @SuppressWarnings("deprecation")
    @Test
    void testAjouterJeu() {
        // Simulation de l'appel de gameService.saveGame()
        doNothing().when(gameService).saveGame(game1);

        // Appel de la méthode du contrôleur
        ResponseEntity<String> response = gameController.ajouterJeu(game1);

        // Vérifications
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Jeu ajouté avec succès.", response.getBody());

        // Vérifie que gameService.saveGame() a été appelée une fois
        verify(gameService, times(1)).saveGame(game1);
    }

    @SuppressWarnings("deprecation")
    @Test
    void testAjouterJeu_WithNullGame() {
        // Appel de la méthode avec un jeu null
        ResponseEntity<String> response = gameController.ajouterJeu(null);

        // Vérification de la réponse
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Le jeu est invalide.", response.getBody());
    }
}
