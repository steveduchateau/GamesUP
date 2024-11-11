package com.gamesUP.gamesUP.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class PurchaseLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int utilisateurId;
    private int jeuId;
    private double prix;

    // Constructeur par défaut
    public PurchaseLine() {
    }

    // Constructeur avec paramètres
    public PurchaseLine(int id, int utilisateurId, int jeuId, double prix) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.jeuId = jeuId;
        this.prix = prix;
    }

    // Getter pour id
    public int getId() {
        return id;
    }

    // Setter pour id
    public void setId(int id) {
        this.id = id;
    }

    // Getter pour utilisateurId
    public int getUtilisateurId() {
        return utilisateurId;
    }

    // Setter pour utilisateurId
    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    // Getter pour jeuId
    public int getJeuId() {
        return jeuId;
    }

    // Setter pour jeuId
    public void setJeuId(int jeuId) {
        this.jeuId = jeuId;
    }

    // Getter pour prix
    public double getPrix() {
        return prix;
    }

    // Setter pour prix
    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Méthode toString pour afficher l'objet
    @Override
    public String toString() {
        return "PurchaseLine{id=" + id + ", utilisateurId=" + utilisateurId +
                ", jeuId=" + jeuId + ", prix=" + prix + "}";
    }
}
