package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    // Méthode pour récupérer un inventaire par son ID
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    // Méthode pour ajouter un nouvel inventaire
    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Méthode pour ajouter du stock pour un jeu spécifique
    public void addStock(Long inventoryId, Game game, int quantity) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);
        inventoryOptional.ifPresent(inventory -> {
            inventory.addStock(game, quantity);
            inventoryRepository.save(inventory);
        });
    }

    // Méthode pour enlever du stock pour un jeu spécifique
    public void removeStock(Long inventoryId, Game game, int quantity) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);
        inventoryOptional.ifPresent(inventory -> {
            inventory.removeStock(game, quantity);
            inventoryRepository.save(inventory);
        });
    }
}
