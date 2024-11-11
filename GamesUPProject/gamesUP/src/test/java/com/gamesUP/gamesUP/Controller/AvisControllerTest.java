package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.Service.AvisService;
import com.gamesUP.gamesUP.controller.AvisController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvisControllerTest {

    @Mock
    private AvisService avisService;

    @InjectMocks
    private AvisController avisController;

    private Avis avis;

    @BeforeEach
    void setUp() {
        avis = new Avis();
        avis.setId(1L);
        avis.setCommentaire("Avis Test");  // Utilisation du nom correct 'setCommentaire'
        avis.setNote(4);  // Utilisation du champ 'note' au lieu de 'auteur'
        avis.setUser(new User());  // Assurez-vous d'ajouter un utilisateur valide ici
    }

    @SuppressWarnings("null")
    @Test
    void testGetAvisById_Exists() {
        when(avisService.getAvisById(1L)).thenReturn(Optional.of(avis));

        ResponseEntity<Avis> response = avisController.getAvisById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(avis.getId(), response.getBody().getId());
        verify(avisService, times(1)).getAvisById(1L);
    }

    @Test
    void testGetAvisById_NotFound() {
        when(avisService.getAvisById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Avis> response = avisController.getAvisById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(avisService, times(1)).getAvisById(1L);
    }

    @SuppressWarnings("null")
    @Test
    void testAddAvis() {
        when(avisService.addAvis(any(Avis.class))).thenReturn(avis);

        ResponseEntity<Avis> response = avisController.addAvis(avis);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Avis Test", response.getBody().getCommentaire());  // Utilisation de 'getCommentaire'
        verify(avisService, times(1)).addAvis(any(Avis.class));
    }

    @SuppressWarnings("null")
    @Test
    void testUpdateAvis() {
        Avis updatedAvis = new Avis();
        updatedAvis.setId(1L);
        updatedAvis.setCommentaire("Avis mis à jour");
        updatedAvis.setNote(5);
        updatedAvis.setUser(new User());  // Ajouter un utilisateur ici aussi

        when(avisService.updateAvis(eq(1L), any(Avis.class))).thenReturn(updatedAvis);

        ResponseEntity<Avis> response = avisController.updateAvis(1L, updatedAvis);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Avis mis à jour", response.getBody().getCommentaire());  // Utilisation de 'getCommentaire'
        verify(avisService, times(1)).updateAvis(eq(1L), any(Avis.class));
    }

    @Test
    void testUpdateAvis_NotFound() {
        Avis updatedAvis = new Avis();
        updatedAvis.setId(1L);
        updatedAvis.setCommentaire("Avis mis à jour");
        updatedAvis.setNote(5);
        updatedAvis.setUser(new User());

        when(avisService.updateAvis(eq(1L), any(Avis.class))).thenReturn(null);

        ResponseEntity<Avis> response = avisController.updateAvis(1L, updatedAvis);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(avisService, times(1)).updateAvis(eq(1L), any(Avis.class));
    }

    @Test
    void testDeleteAvis() {
        doNothing().when(avisService).deleteAvis(1L);

        ResponseEntity<Void> response = avisController.deleteAvis(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(avisService, times(1)).deleteAvis(1L);
    }
}
