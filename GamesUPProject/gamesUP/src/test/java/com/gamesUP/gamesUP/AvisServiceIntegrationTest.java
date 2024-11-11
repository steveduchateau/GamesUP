package com.gamesUP.gamesUP;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.Service.AvisService;
import com.gamesUP.gamesUP.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test") // Utilisation d'un profil de test (optionnel)
class AvisServiceIntegrationTest {

    @Autowired
    private AvisService avisService;

    @Autowired
    private UserRepository userRepository; // Repository pour gérer les utilisateurs

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Création d'un utilisateur de test
        testUser = new User(null, "Test User", "testuser", "password", "testuser@example.com");

        // Sauvegarder l'utilisateur avant d'essayer d'ajouter des avis
        userRepository.save(testUser);  // Persister l'utilisateur avant de l'utiliser dans Avis
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testAddAvis() {
        // Création d'un avis en associant l'utilisateur déjà persistant
        Avis avis = new Avis("Excellent jeu!", 5, testUser);

        // Sauvegarde de l'avis
        Avis savedAvis = avisService.addAvis(avis);

        // Vérification que l'avis est bien sauvegardé
        assertNotNull(savedAvis);
        assertNotNull(savedAvis.getId());
        assertEquals("Excellent jeu!", savedAvis.getCommentaire());
        assertEquals(5, savedAvis.getNote());
    }

    @Test
    public void testGetAvisById() {
        // Création d'un avis en associant l'utilisateur déjà persistant
        Avis avis = new Avis("Super expérience!", 4, testUser);
        Avis savedAvis = avisService.addAvis(avis);

        // Récupération de l'avis par ID
        Optional<Avis> foundAvis = avisService.getAvisById(savedAvis.getId());

        // Vérification que l'avis est bien récupéré
        assertTrue(foundAvis.isPresent());
        assertEquals("Super expérience!", foundAvis.get().getCommentaire());
        assertEquals(4, foundAvis.get().getNote());
    }

    @Test
    public void testDeleteAvis() {
        // Création d'un avis en associant l'utilisateur déjà persistant
        Avis avis = new Avis("Moyenne expérience", 3, testUser);
        Avis savedAvis = avisService.addAvis(avis);

        // Suppression de l'avis
        avisService.deleteAvis(savedAvis.getId());

        // Vérification que l'avis a été supprimé
        Optional<Avis> deletedAvis = avisService.getAvisById(savedAvis.getId());
        assertFalse(deletedAvis.isPresent());
    }
}
