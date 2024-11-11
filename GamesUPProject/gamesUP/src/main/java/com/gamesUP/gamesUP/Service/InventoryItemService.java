package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.InventoryItem;
import com.gamesUP.gamesUP.Repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryItemService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    // Méthode pour récupérer un élément d'inventaire par son ID
    public Optional<InventoryItem> getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id);
    }

    // Méthode pour ajouter un nouvel élément d'inventaire
    public InventoryItem addInventoryItem(InventoryItem inventoryItem) {
        return inventoryItemRepository.save(inventoryItem);
    }

    // Méthode pour mettre à jour la quantité d'un élément d'inventaire
    public void updateQuantity(Long inventoryItemId, int newQuantity) {
        Optional<InventoryItem> inventoryItemOptional = inventoryItemRepository.findById(inventoryItemId);
        inventoryItemOptional.ifPresent(inventoryItem -> {
            inventoryItem.setQuantity(newQuantity);
            inventoryItemRepository.save(inventoryItem);
        });
    }
}
