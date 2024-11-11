package com.gamesUP.gamesUP.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    // Déclaration de la variable 'id' comme clé primaire
    @Id
    private Long id;

    // Déclaration de la variable 'type' de type String
    private String type;

    // Constructeur sans argument pour créer une instance de Category
    public Category() {
    }

    // Constructeur avec arguments pour initialiser 'id' et 'type'
    public Category(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    // Getter pour récupérer la valeur de 'id'
    public Long getId() {
        return id;
    }

    // Setter pour modifier la valeur de 'id'
    public void setId(Long id) {
        this.id = id;
    }

    // Getter pour récupérer la valeur de 'type'
    public String getType() {
        return type;
    }

    // Setter pour modifier la valeur de 'type'
    public void setType(String type) {
        this.type = type;
    }

    // Méthode toString() pour afficher une représentation textuelle de l'objet
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
