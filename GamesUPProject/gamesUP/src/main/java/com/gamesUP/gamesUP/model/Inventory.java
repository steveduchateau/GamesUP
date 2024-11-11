package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id") // Créer une relation bidirectionnelle si nécessaire
    private Set<InventoryItem> items = new HashSet<>();

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<InventoryItem> getItems() {
        return items;
    }

    public void setItems(Set<InventoryItem> items) {
        this.items = items;
    }

    // Constructeur par défaut
    public Inventory() {
    }

    // Méthode utilitaire pour ajouter un stock pour un jeu
    public void addStock(Game game, int quantity) {
        InventoryItem item = this.items.stream()
            .filter(i -> i.getGame().equals(game))
            .findFirst()
            .orElse(new InventoryItem());
        item.setGame(game);
        item.setQuantity(item.getQuantity() + quantity);
        this.items.add(item);
    }

    // Méthode utilitaire pour enlever un stock pour un jeu
    public void removeStock(Game game, int quantity) {
        InventoryItem item = this.items.stream()
            .filter(i -> i.getGame().equals(game))
            .findFirst()
            .orElse(null);
        if (item != null) {
            int currentStock = item.getQuantity();
            int newStock = Math.max(currentStock - quantity, 0); // Stock ne peut pas être négatif
            item.setQuantity(newStock);
        }
    }
}
