package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.DTO.UserDTO;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Repository.UserRepository;
import com.gamesUP.gamesUP.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Méthode pour récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Méthode pour récupérer un utilisateur par son ID
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // Méthode pour ajouter un nouvel utilisateur
    public User addUser(User user) {
        // Vérifier que l'email et le mot de passe ne sont pas vides
        if (user.getEmail() == null || user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("L'email et le mot de passe sont requis.");
        }

        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Déterminer le rôle en fonction de l'email
        Role role = determineRoleFromEmail(user.getEmail());

        // Ajouter le rôle à l'utilisateur
        user.setRoles(Set.of(role));

        // Sauvegarder l'utilisateur dans la base de données
        return userRepository.save(user);
    }

    // Méthode pour déterminer le rôle en fonction de l'email
    private Role determineRoleFromEmail(String email) {
        // Exemple : Si l'email appartient au domaine 'gamesup.com', l'utilisateur est ADMIN
        if (email.endsWith("@gamesup.com")) {
            return roleRepository.findByName("ADMIN").orElseThrow(() -> new IllegalArgumentException("Role ADMIN not found"));
        } else {
            return roleRepository.findByName("CLIENT").orElseThrow(() -> new IllegalArgumentException("Role CLIENT not found"));
        }
    }

    // Méthode pour mettre à jour un utilisateur
    public User updateUser(int id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNom(userDetails.getNom());
            user.setUsername(userDetails.getUsername());
            user.setRoles(userDetails.getRoles());
            return userRepository.save(user);
        }
        return null;
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Méthode pour récupérer un utilisateur par son nom
    public Optional<User> getUserByNom(String nom) {
        return userRepository.findByNom(nom);
    }

    // Méthode pour récupérer un utilisateur par son nom d'utilisateur
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Méthode pour convertir un User en UserDTO
    public UserDTO convertToDTO(User user) {
        Set<String> roleNames = user.getRoles().stream()
                                    .map(Role::getName)
                                    .collect(Collectors.toSet());
        return new UserDTO(user.getId(), user.getNom(), user.getUsername(), roleNames);
    }

    // Méthode pour convertir un UserDTO en User
    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setNom(userDTO.getNom());
        user.setUsername(userDTO.getUsername());
        return user;
    }
}
