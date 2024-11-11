package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Endpoint pour récupérer un inventaire par son ID
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        Optional<Inventory> inventory = inventoryService.getInventoryById(id);
        return inventory.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter un nouvel inventaire (réservé à l'admin)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory newInventory = inventoryService.addInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(newInventory);
    }

    // Endpoint pour ajouter du stock pour un jeu spécifique dans un inventaire (réservé à l'admin)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{inventoryId}/add-stock")
    public ResponseEntity<Void> addStock(@PathVariable Long inventoryId, @RequestParam Long gameId, @RequestParam int quantity) {
        Game game = new Game();  // Charger le jeu en fonction de l'ID (à ajuster selon votre logique)
        game.setId(gameId);
        inventoryService.addStock(inventoryId, game, quantity);
        return ResponseEntity.ok().build();
    }

    // Endpoint pour enlever du stock pour un jeu spécifique dans un inventaire (réservé à l'admin)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{inventoryId}/remove-stock")
    public ResponseEntity<Void> removeStock(@PathVariable Long inventoryId, @RequestParam Long gameId, @RequestParam int quantity) {
        Game game = new Game();  // Charger le jeu en fonction de l'ID (à ajuster selon votre logique)
        game.setId(gameId);
        inventoryService.removeStock(inventoryId, game, quantity);
        return ResponseEntity.ok().build();
    }
}
