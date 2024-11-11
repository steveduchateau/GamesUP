package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.DTO.UserDTO;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Repository.UserRepository;
import com.gamesUP.gamesUP.Repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Role role;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation des objets mocks
        user = new User();
        user.setId(1L);  // Assurez-vous que l'id est de type Long
        user.setNom("Steve");
        user.setUsername("steve123");
        user.setEmail("steve@gamesup.com");
        user.setPassword("password123");

        role = new Role();
        role.setName("ADMIN");
    }

    @Test
    public void testAddUser() {
        // Simuler le comportement des mocks
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(role));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Appel de la méthode
        User savedUser = userService.addUser(user);

        // Vérification des interactions et assertions
        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        assertTrue(savedUser.getRoles().contains(role));

        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testConvertToDTO() {
        // Simuler la conversion en DTO
        Set<String> roleNames = Set.of("ADMIN");
        user.setRoles(Set.of(role));

        UserDTO userDTO = userService.convertToDTO(user);

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getNom(), userDTO.getNom());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(roleNames, userDTO.getRoles());  // Correction ici, utiliser getRoles()
    }

    // Autres tests pour les autres méthodes...
}
