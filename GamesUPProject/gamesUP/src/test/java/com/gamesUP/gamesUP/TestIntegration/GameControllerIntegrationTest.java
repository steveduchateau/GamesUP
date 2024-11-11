package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.controller.GameController;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.Service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class GameControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    @Test
    public void testAjouterJeu() throws Exception {
        doNothing().when(gameService).saveGame(any(Game.class)); // Utilisation de `any(Game.class)` pour ignorer les détails spécifiques de l'argument

        mockMvc.perform(post("/games")
                .contentType("application/json")
                .content("{\"title\":\"New Game\",\"author\":null}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Jeu ajouté avec succès."));
    }

    @Test
    public void testUpdateGame() throws Exception {
        // Simuler la mise à jour d'un jeu existant
        Game existingGame = new Game("Existing Game", null);
        when(gameService.getGameById(1L)).thenReturn(existingGame);
        doNothing().when(gameService).updateGame(any(Long.class), any(Game.class)); // Utilisation de `any()` pour ignorer les arguments spécifiques

        mockMvc.perform(put("/games/{id}", 1L)
                .contentType("application/json")
                .content("{\"title\":\"Updated Game\",\"author\":null}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Jeu mis à jour avec succès."));
    }

    @Test
    public void testUpdateGameNotFound() throws Exception {
        when(gameService.getGameById(999L)).thenReturn(null);

        mockMvc.perform(put("/games/{id}", 999L)
                .contentType("application/json")
                .content("{\"title\":\"Non Existent Game\",\"author\":null}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Le jeu avec l'ID 999 n'a pas été trouvé."));
    }
}
