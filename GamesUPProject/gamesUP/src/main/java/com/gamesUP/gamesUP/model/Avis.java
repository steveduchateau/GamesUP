package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;

@Entity
@Table(name = "avis")
public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentaire;

    @Column(nullable = false)
    private int note;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Constructeur par défaut
    public Avis() {}

    // Constructeur avec paramètres
    public Avis(String commentaire, int note, User user) {
        this.commentaire = commentaire;
        this.note = note;
        this.user = user;
    }

    // Redéfinir la méthode toString()
    @Override
    public String toString() {
        return "Avis{commentaire='" + commentaire + "', note=" + note + ", user=" + user + "}";
    }
}
