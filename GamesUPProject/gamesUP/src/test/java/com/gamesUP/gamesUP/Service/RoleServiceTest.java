package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role("client");
    }

    @Test
    void testInitRoles() {
        // Arrange: Mock behavior to simulate roles not existants
        when(roleRepository.findByName("client")).thenReturn(Optional.empty());
        when(roleRepository.findByName("admin")).thenReturn(Optional.empty());

        // Act: Initialize roles
        roleService.initRoles();

        // Assert: Verify that the roles are saved
        // Vérifiez que la méthode save() est appelée avec un objet Role sans ID (ID == null)
        verify(roleRepository, times(1)).save(argThat(roleArg -> roleArg.getName().equals("client") && roleArg.getId() == null));
        verify(roleRepository, times(1)).save(argThat(roleArg -> roleArg.getName().equals("admin") && roleArg.getId() == null));
    }

    @Test
    void testGetRoleByName() {
        // Arrange: Mock behavior to return a role
        when(roleRepository.findByName("client")).thenReturn(Optional.of(role));

        // Act: Fetch role by name
        Optional<Role> result = roleService.getRoleByName("client");

        // Assert: Verify that the correct role is returned
        assertTrue(result.isPresent());
        assertEquals("client", result.get().getName());
    }

    @Test
    void testAddRole() {
        // Arrange: Mock the save method
        when(roleRepository.save(role)).thenReturn(role);

        // Act: Add the role
        Role savedRole = roleService.addRole(role);

        // Assert: Verify that the role is saved
        assertNotNull(savedRole);
        assertEquals("client", savedRole.getName());
    }

    @Test
    void testUpdateRole() {
        // Arrange: Mock the repository methods
        when(roleRepository.findById(1)).thenReturn(Optional.of(role));
        when(roleRepository.save(role)).thenReturn(role);

        // Act: Update role
        role.setName("admin");
        Role updatedRole = roleService.updateRole(1, role);

        // Assert: Verify that the role is updated
        assertNotNull(updatedRole);
        assertEquals("admin", updatedRole.getName());
    }

    @Test
    void testDeleteRole() {
        // Act: Delete role by ID
        roleService.deleteRole(1);

        // Assert: Verify that the delete method was called once
        verify(roleRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetAllRoles() {
        // Arrange: Create a list of roles and mock the repository method
        when(roleRepository.findAll()).thenReturn(List.of(role));

        // Act: Fetch all roles
        List<Role> roles = roleService.getAllRoles();

        // Assert: Verify that the roles are returned
        assertNotNull(roles);
        assertEquals(1, roles.size());
        assertEquals("client", roles.get(0).getName());
    }
}
