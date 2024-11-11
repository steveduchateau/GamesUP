package com.gamesUP.gamesUP.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String username;
    private String password;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
      name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonManagedReference  // Côté propriétaire de la relation
    private Set<Role> roles = new HashSet<>();

    // Constructeur par défaut
    public User() {}

    // Constructeur avec paramètres
    public User(Long id, String nom, String username, String password, String email) {
        this.id = id;
        this.nom = nom;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters et setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    // Méthode pour obtenir un rôle spécifique
    public String getRole() {
        if (this.roles != null && !this.roles.isEmpty()) {
            return this.roles.iterator().next().getName(); // Renvoie le nom du premier rôle de l'utilisateur
        }
        return null;
    }

    // Méthode pour définir un rôle spécifique
    public void setRole(Role role) {
        this.roles.add(role);
    }
}
