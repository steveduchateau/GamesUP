package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.Repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    private Game game1;
    private Game game2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game1 = new Game("Game 1", new Author());
        game2 = new Game("Game 2", new Author());
    }

    @Test
    void testGetAllGames() {
        // Liste simulée de jeux
        List<Game> games = Arrays.asList(game1, game2);

        // Simulation de la méthode findAll() de GameRepository
        when(gameRepository.findAll()).thenReturn(games);

        // Appel de la méthode dans GameService
        List<Game> result = gameService.getAllGames();

        // Vérifications
        assertEquals(2, result.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void testSaveGame() {
        // Simulation du retour de la méthode save() de GameRepository
        when(gameRepository.save(game1)).thenReturn(game1); // Simuler le retour du même jeu sauvegardé

        // Appel de la méthode saveGame
        gameService.saveGame(game1);

        // Vérification que la méthode save() a été appelée une fois
        verify(gameRepository, times(1)).save(game1);
    }

    @Test
    void testSaveGame_WithNullGame() {
        // Test avec un jeu null
        gameService.saveGame(null);

        // Vérification que la méthode save() n'a pas été appelée
        verify(gameRepository, times(0)).save(any());
    }
}
