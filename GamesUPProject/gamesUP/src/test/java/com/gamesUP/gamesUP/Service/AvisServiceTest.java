package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.Repository.AvisRepository;
import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.Optional;

class AvisServiceTest {

    @Mock
    private AvisRepository avisRepository;

    @Mock
    private UserDetailsService userDetailsService;  // Mock du service qui gère les utilisateurs

    @InjectMocks
    private AvisService avisService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testIsAvisOwner() {
        // Id de l'avis à tester
        Long avisId = 1L;

        // Création de l'utilisateur (votre modèle User)
        User user = new User(1L, "Nom", "user1", "password", "user1@example.com");

        // Création d'un avis associé à cet utilisateur
        Avis avis = new Avis();
        avis.setId(avisId);
        avis.setUser(user); // Association de l'avis avec l'utilisateur

        // Simulez la récupération de l'avis à partir du repository
        when(avisRepository.findById(avisId)).thenReturn(Optional.of(avis));

        // Créez un UserDetails qui correspond à l'utilisateur, sans devoir le caster
        UserDetails userDetails = org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .roles("USER")  // Ajoutez les rôles si nécessaire
            .build();

        // Simulez l'appel du service pour obtenir l'utilisateur
        when(userDetailsService.loadUserByUsername(user.getUsername())).thenReturn(userDetails);

        // Appel du service pour vérifier si l'utilisateur est propriétaire de l'avis
        boolean isOwner = avisService.isAvisOwner(avisId, userDetails);

        // Vérification que l'utilisateur est bien le propriétaire
        assertTrue(isOwner);
    }
}
