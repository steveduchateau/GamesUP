package com.gamesUP.gamesUP;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.Repository.RoleRepository;
import com.gamesUP.gamesUP.Repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        // Création ou récupération des rôles
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> {
            Role newAdminRole = new Role("ADMIN");
            roleRepository.save(newAdminRole);
            return newAdminRole;
        });

        Role userRole = roleRepository.findByName("USER").orElseGet(() -> {
            Role newUserRole = new Role("USER");
            roleRepository.save(newUserRole);
            return newUserRole;
        });

        // Création des utilisateurs avec un mot de passe
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();  // Ajout du mot de passe
            admin.getRoles().add(adminRole);  // Ajout du rôle
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();  // Ajout du mot de passe
            user.getRoles().add(userRole);  // Ajout du rôle
            userRepository.save(user);
        }
    }
}
