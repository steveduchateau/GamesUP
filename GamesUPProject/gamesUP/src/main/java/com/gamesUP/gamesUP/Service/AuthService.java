package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Repository.UserRepository;
import com.gamesUP.gamesUP.Repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service // L'annotation @Service permet à Spring de gérer ce service comme un bean.
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;  // Utilisez PasswordEncoder au lieu de BCryptPasswordEncoder

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {
        String roleName = username.contains("gamesup") ? "ADMIN" : "CLIENT";

        Role role = roleRepository.findByName(roleName)
            .orElseGet(() -> {
                Role newRole = new Role(roleName);
                roleRepository.save(newRole);
                return newRole;
            });

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));  // Encodage du mot de passe avec le PasswordEncoder

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
