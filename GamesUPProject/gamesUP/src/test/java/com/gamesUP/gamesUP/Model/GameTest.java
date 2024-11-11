package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Game;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private Game game;
    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();  // Créer un auteur pour les tests
        game = new Game("Test Game", author);  // Créer un jeu pour les tests
    }

    @Test
    void testGettersAndSetters() {
        // Test des getters et setters
        game.setTitle("New Title");
        game.setAuthor(author);

        // Vérifications
        assertEquals("New Title", game.getTitle());
        assertEquals(author, game.getAuthor());
    }

    @Test
    void testGameConstructor() {
        // Test du constructeur
        Game newGame = new Game("New Game", author);
        assertEquals("New Game", newGame.getTitle());
        assertEquals(author, newGame.getAuthor());
    }

    @Test
    void testDefaultConstructor() {
        // Test du constructeur par défaut
        Game newGame = new Game();
        newGame.setTitle("Game with Default Constructor");

        // Vérification du titre après avoir défini
        assertEquals("Game with Default Constructor", newGame.getTitle());
    }
}
