package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.model.User;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private Role roleAdmin;
    private Role roleUser;

    @BeforeEach
    void setUp() {
        // Création d'un utilisateur
        user = new User();
        user.setNom("John Doe");
        user.setUsername("johndoe");
        user.setPassword("password123");
        user.setEmail("johndoe@example.com");

        // Création de rôles
        roleAdmin = new Role();
        roleAdmin.setId(1L);
        roleAdmin.setName("ADMIN");

        roleUser = new Role();
        roleUser.setId(2L);
        roleUser.setName("USER");
    }

    @Test
    void testAddRole() {
        // Ajouter un rôle à l'utilisateur
        user.addRole(roleAdmin);

        assertTrue(user.getRoles().contains(roleAdmin));  // Vérifie que le rôle est ajouté
        assertEquals("ADMIN", user.getRole());  // Vérifie que la méthode getRole() retourne le bon rôle
    }

    @Test
    void testRemoveRole() {
        // Ajouter un rôle et puis le retirer
        user.addRole(roleAdmin);
        user.removeRole(roleAdmin);

        assertFalse(user.getRoles().contains(roleAdmin));  // Vérifie que le rôle est bien retiré
    }

    @Test
    void testGetRoleWhenNoRoles() {
        // Tester le cas où l'utilisateur n'a pas de rôle
        assertNull(user.getRole());  // Vérifie que la méthode getRole() retourne null si aucun rôle n'est ajouté
    }

    @Test
    void testSetRole() {
        // Tester la méthode setRole
        user.setRole(roleUser);
        assertTrue(user.getRoles().contains(roleUser));  // Vérifie que le rôle est ajouté
    }

    @Test
    void testMultipleRoles() {
        // Tester avec plusieurs rôles
        user.addRole(roleAdmin);
        user.addRole(roleUser);

        // Vérifie que les deux rôles sont présents
        assertTrue(user.getRoles().contains(roleAdmin));  // Vérifie que le rôle ADMIN est présent
        assertTrue(user.getRoles().contains(roleUser));  // Vérifie que le rôle USER est présent

        // Vérifie que getRole() retourne bien l'un des rôles ajoutés
        String role = user.getRole();
        assertTrue(role.equals("ADMIN") || role.equals("USER"), "Le rôle retourné doit être ADMIN ou USER, mais c'est : " + role);
    }
}
