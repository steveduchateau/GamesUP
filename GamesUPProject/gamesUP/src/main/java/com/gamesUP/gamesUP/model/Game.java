package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id est maintenant de type Long pour correspondre à votre test

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)  // Cette colonne doit exister dans la table "games"
    private Author author;

    // Constructeur par défaut
    public Game() {
    }

    // Constructeur avec paramètres
    public Game(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // Getters et Setters
    public Long getId() {
        return id; // Le type de retour est Long
    }

    public void setId(Long id) {
        this.id = id; // Paramètre de type Long
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Game{id=" + id + ", title='" + title + "', author=" + author + "}";
    }
}
