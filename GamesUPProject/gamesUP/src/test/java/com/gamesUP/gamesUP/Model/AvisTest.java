package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.model.User;

import static org.junit.jupiter.api.Assertions.*;

class AvisTest {

    private Avis avis;
    private User user;

    @BeforeEach
    void setUp() {
        // Utilisation du constructeur avec tous les paramètres pour créer l'utilisateur
        user = new User(null, "Nom", "username", "password", "user@example.com");
        
        // Création d'un avis avec un commentaire et une note
        avis = new Avis("Super jeu !", 5, user);
    }

    @Test
    void testAvisConstructorAndGetters() {
        // Vérifier que l'ID est null car l'objet n'est pas encore persisté
        assertNull(avis.getId());
        
        // Vérifier que le commentaire, la note et l'utilisateur sont correctement définis
        assertEquals("Super jeu !", avis.getCommentaire());
        assertEquals(5, avis.getNote());
        assertEquals(user, avis.getUser());
    }

    @Test
    void testSetters() {
        // Utilisation des setters pour modifier les valeurs
        avis.setCommentaire("Moyenne prestation");
        avis.setNote(3);
        
        // Vérifier que les setters ont correctement modifié les valeurs
        assertEquals("Moyenne prestation", avis.getCommentaire());
        assertEquals(3, avis.getNote());
    }

    @Test
    void testAvisToString() {
        // Méthode toString (si définie, sinon vous pouvez l'implémenter pour tester)
        String expectedString = "Avis{commentaire='Super jeu !', note=5, user=" + user + "}";
        assertEquals(expectedString, avis.toString());
    }

    @Test
    void testAvisDefaultConstructor() {
        // Tester le constructeur par défaut
        Avis avisDefault = new Avis();
        
        // Vérifier que l'objet est correctement créé sans valeurs initiales
        assertNull(avisDefault.getCommentaire());
        assertEquals(0, avisDefault.getNote()); // Note par défaut à 0
        assertNull(avisDefault.getUser());
    }
}
