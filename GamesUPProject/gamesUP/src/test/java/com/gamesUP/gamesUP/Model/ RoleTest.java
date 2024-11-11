package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Role;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testToString() {
        Role role = new Role("Admin");
        role.setId(1L);
        
        // Vérifier que la méthode toString retourne une chaîne avec les valeurs attendues
        String expectedString = "Role{id=1, name='Admin'}";
        assertEquals(expectedString, role.toString());
    }
}
