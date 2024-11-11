package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Lien vers l'utilisateur qui possède cette liste de souhaits

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "wishlist_game", 
        joinColumns = @JoinColumn(name = "wishlist_id"), 
        inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games;  // Liste des jeux présents dans la wishlist

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    // Constructeur par défaut
    public Wishlist() {
    }

    // Constructeur avec paramètres
    public Wishlist(User user, List<Game> games) {
        this.user = user;
        this.games = games;
    }
}
