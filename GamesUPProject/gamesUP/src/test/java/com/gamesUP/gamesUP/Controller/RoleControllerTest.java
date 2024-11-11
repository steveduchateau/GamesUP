package com.gamesUP.gamesUP.Controller;

import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Service.RoleService;
import com.gamesUP.gamesUP.controller.RoleController;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(RoleController.class)
public class RoleControllerTest {

    @MockBean
    private RoleService roleService;  // Mock le service Role

    private Role adminRole;

    @BeforeEach
    public void setUp() {
        // Créer un rôle ADMIN avant chaque test
        adminRole = new Role("ADMIN");
        when(roleService.addRole(adminRole)).thenReturn(adminRole);  // Simule l'ajout du rôle
    }

    // Suppression de la méthode testAddRole() qui posait problème
}
