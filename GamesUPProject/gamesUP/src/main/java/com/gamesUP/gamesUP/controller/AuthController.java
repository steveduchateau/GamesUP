package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.model.Role;
import com.gamesUP.gamesUP.Service.UserService;
import com.gamesUP.gamesUP.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;  // Ajout du service pour gérer les rôles

    // Endpoint pour l'inscription d'un utilisateur
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user, @RequestParam(required = false) String roleName) {
        // Chiffrement du mot de passe avant de l'enregistrer
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Par défaut, "client", mais peut être changé pour "admin" si spécifié
        Role userRole = roleService.getRoleByName(roleName != null ? roleName : "client")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Ajouter le rôle à l'utilisateur
        user.addRole(userRole);

        // Sauvegarder l'utilisateur avec son rôle
        User newUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Endpoint pour la connexion d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        // Normalement, la gestion de la connexion se fait par Spring Security
        return ResponseEntity.ok("User logged in successfully");
    }
}
