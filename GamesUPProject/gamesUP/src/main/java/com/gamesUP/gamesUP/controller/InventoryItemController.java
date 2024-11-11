package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.InventoryItem;
import com.gamesUP.gamesUP.Service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventory-items")
public class InventoryItemController {

    @Autowired
    private InventoryItemService inventoryItemService;

    // Endpoint pour récupérer un élément d'inventaire par son ID, accessible par les ADMIN et par le client lié à l'élément
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @inventoryItemService.isUserInventoryItemOwner(#id)")  // L'utilisateur peut consulter son propre élément ou tous les éléments en tant qu'ADMIN
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
        Optional<InventoryItem> inventoryItem = inventoryItemService.getInventoryItemById(id);
        return inventoryItem.map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter un nouvel élément d'inventaire, uniquement accessible par les ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")  // Seul un ADMIN peut ajouter un élément d'inventaire
    public ResponseEntity<InventoryItem> addInventoryItem(@RequestBody InventoryItem inventoryItem) {
        InventoryItem newInventoryItem = inventoryItemService.addInventoryItem(inventoryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInventoryItem);
    }

    // Endpoint pour mettre à jour la quantité d'un élément d'inventaire, uniquement accessible par les ADMIN
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Seul un ADMIN peut mettre à jour un élément d'inventaire
    public ResponseEntity<Void> updateQuantity(@PathVariable Long id, @RequestParam int quantity) {
        inventoryItemService.updateQuantity(id, quantity);
        return ResponseEntity.ok().build();
    }
}
