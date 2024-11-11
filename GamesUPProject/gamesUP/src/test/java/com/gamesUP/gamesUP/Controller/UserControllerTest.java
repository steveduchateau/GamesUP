package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.DTO.UserDTO;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.Service.UserService;
import com.gamesUP.gamesUP.controller.UserController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Création d'un utilisateur pour les tests
        user = new User(1L, "John Doe", "john_doe", "password123", "john.doe@example.com");
        Set<String> roles = Set.of("ROLE_USER");  // Exemple de rôle
        userDTO = new UserDTO(1L, "John Doe", "john_doe", roles);  // Utilisez Set<String> pour les rôles
    }

    @Test
    public void testGetUserById_Success() {
        // Lorsque le service retourne un utilisateur
        when(userService.getUserById(1)).thenReturn(Optional.of(user));  // Remplacez 1L par 1
        when(userService.convertToDTO(user)).thenReturn(userDTO);

        // Appel du contrôleur
        ResponseEntity<UserDTO> response = userController.getUserById(1);

        // Vérification du résultat
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testGetUserById_NotFound() {
        // Lorsque le service ne trouve pas l'utilisateur
        when(userService.getUserById(1)).thenReturn(Optional.empty());  // Remplacez 1L par 1

        // Appel du contrôleur
        ResponseEntity<UserDTO> response = userController.getUserById(1);

        // Vérification du résultat
        assertEquals(404, response.getStatusCode().value());  // Utilisez .value() au lieu de .getStatusCodeValue()
    }

    @Test
    public void testAddUser_Success() {
        // Lorsque le service ajoute un utilisateur
        when(userService.convertToEntity(userDTO)).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);
        when(userService.convertToDTO(user)).thenReturn(userDTO);

        // Appel du contrôleur
        ResponseEntity<UserDTO> response = userController.addUser(userDTO);

        // Vérification du résultat
        assertEquals(201, response.getStatusCode().value());  // Utilisez .value() au lieu de .getStatusCodeValue()
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testUpdateUser_Success() {
        // Lorsque le service met à jour un utilisateur
        when(userService.convertToEntity(userDTO)).thenReturn(user);
        when(userService.updateUser(1, user)).thenReturn(user);  // Remplacez 1L par 1
        when(userService.convertToDTO(user)).thenReturn(userDTO);

        // Appel du contrôleur
        ResponseEntity<UserDTO> response = userController.updateUser(1, userDTO);

        // Vérification du résultat
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testDeleteUser_Success() {
        // Lors de la suppression d'un utilisateur
        doNothing().when(userService).deleteUser(1);  // Remplacez 1L par 1

        // Appel du contrôleur
        ResponseEntity<Void> response = userController.deleteUser(1);

        // Vérification du résultat
        assertEquals(204, response.getStatusCode().value());  // Utilisez .value() au lieu de .getStatusCodeValue()
    }
}
