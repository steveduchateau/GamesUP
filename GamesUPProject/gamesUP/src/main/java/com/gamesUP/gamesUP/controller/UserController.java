package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.DTO.UserDTO;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint pour récupérer tous les utilisateurs (réservé aux administrateurs)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream()
                          .map(userService::convertToDTO)
                          .collect(Collectors.toList());
    }

    // Endpoint pour récupérer un utilisateur par son ID (accessible uniquement à l'utilisateur lui-même ou à un administrateur)
    @PreAuthorize("hasRole('ADMIN') or @userService.isOwner(#id)")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(u -> ResponseEntity.ok(userService.convertToDTO(u)))
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter un nouvel utilisateur (accessible uniquement aux clients pour l'inscription)
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = userService.convertToEntity(userDTO);
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.convertToDTO(newUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    // Endpoint pour mettre à jour un utilisateur (accessible uniquement à l'utilisateur lui-même ou à un administrateur)
    @PreAuthorize("hasRole('ADMIN') or @userService.isOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        User user = userService.convertToEntity(userDTO);
        User updatedUser = userService.updateUser(id, user);
        return updatedUser != null ? ResponseEntity.ok(userService.convertToDTO(updatedUser))
                                   : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer un utilisateur (réservé aux administrateurs)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour récupérer un utilisateur par son nom (accessible uniquement à l'administrateur)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/nom/{nom}")
    public ResponseEntity<UserDTO> getUserByNom(@PathVariable String nom) {
        Optional<User> user = userService.getUserByNom(nom);
        return user.map(u -> ResponseEntity.ok(userService.convertToDTO(u)))
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour récupérer un utilisateur par son nom d'utilisateur (accessible uniquement à l'administrateur)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(u -> ResponseEntity.ok(userService.convertToDTO(u)))
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
