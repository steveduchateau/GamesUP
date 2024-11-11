package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Ajoutez cet import pour que la classe Category soit reconnue
import com.gamesUP.gamesUP.model.Category;

class CategoryTest {

    @Test
    void testConstructorWithoutArguments() {
        // Création d'une instance de Category en utilisant le constructeur sans argument
        Category category = new Category();

        // Vérification que les attributs sont bien initialisés
        assertNull(category.getId(), "L'id doit être null");
        assertNull(category.getType(), "Le type doit être null");
    }

    @Test
    void testConstructorWithArguments() {
        // Création d'une instance de Category avec un constructeur avec arguments
        Category category = new Category(1L, "Board Game");

        // Vérification que les valeurs sont correctement assignées
        assertEquals(1L, category.getId(), "L'id ne correspond pas");
        assertEquals("Board Game", category.getType(), "Le type ne correspond pas");
    }

    @Test
    void testSettersAndGetters() {
        // Création d'une instance de Category
        Category category = new Category();

        // Modification des attributs via les setters
        category.setId(2L);
        category.setType("Card Game");

        // Vérification que les valeurs ont été correctement mises à jour via les getters
        assertEquals(2L, category.getId(), "L'id ne correspond pas après avoir utilisé le setter");
        assertEquals("Card Game", category.getType(), "Le type ne correspond pas après avoir utilisé le setter");
    }

    @Test
    void testToString() {
        // Création d'une instance de Category
        Category category = new Category(1L, "Board Game");

        // Vérification de la méthode toString()
        String expected = "Category{id=1, type='Board Game'}";
        assertEquals(expected, category.toString(), "La méthode toString ne retourne pas la chaîne attendue");
    }
}
