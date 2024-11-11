package com.gamesUP.gamesUP.DTO;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String nom;
    private String username;
    private Set<String> roles;  // On ne garde que les noms des rôles

    // Constructeur sans arguments
    public UserDTO() {}

    // Constructeur avec tous les arguments
    public UserDTO(Long id, String nom, String username, Set<String> roles) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.roles = roles;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
