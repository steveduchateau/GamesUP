package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Wishlist;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WishlistTest {

    private Wishlist wishlist;
    private User user;
    private List<Game> games;

    @BeforeEach
    void setUp() {
        // Initialisation d'un utilisateur fictif
        user = new User();
        user.setId(1L); // Utilisation de Long pour l'ID de l'utilisateur
        user.setUsername("Test User"); // Assurez-vous que setUsername est défini dans User

        // Initialisation d'une liste de jeux fictifs
        games = new ArrayList<>();
        Game game = new Game();
        game.setId(1L); // Utilisation de Long pour l'ID du jeu
        game.setTitle("Test Game"); // Assurez-vous que setTitle est défini dans Game
        games.add(game);

        // Initialisation de la wishlist
        wishlist = new Wishlist(user, games);
    }

    @Test
    void testGetId() {
        wishlist.setId(1L); // Utilisation de Long pour l'ID de la wishlist
        assertEquals(1L, wishlist.getId());
    }

    @Test
    void testGetUser() {
        assertEquals(user, wishlist.getUser());
    }

    @Test
    void testSetUser() {
        User newUser = new User();
        newUser.setId(2L); // Utilisation de Long pour l'ID du nouvel utilisateur
        newUser.setUsername("New User"); // Assurez-vous que setUsername est défini dans User
        wishlist.setUser(newUser);
        assertEquals(newUser, wishlist.getUser());
    }

    @Test
    void testGetGames() {
        assertEquals(games, wishlist.getGames());
    }

    @Test
    void testSetGames() {
        List<Game> newGames = new ArrayList<>();
        Game newGame = new Game();
        newGame.setId(2L); // Utilisation de Long pour l'ID du nouveau jeu
        newGame.setTitle("New Test Game"); // Assurez-vous que setTitle est défini dans Game
        newGames.add(newGame);

        wishlist.setGames(newGames);

        assertEquals(newGames, wishlist.getGames());
    }

    @Test
    void testConstructor() {
        Wishlist newWishlist = new Wishlist(user, games);
        assertNotNull(newWishlist);
        assertEquals(user, newWishlist.getUser());
        assertEquals(games, newWishlist.getGames());
    }

    @Test
    void testEmptyConstructor() {
        Wishlist emptyWishlist = new Wishlist();
        assertNull(emptyWishlist.getUser());
        assertNull(emptyWishlist.getGames());
    }
}
