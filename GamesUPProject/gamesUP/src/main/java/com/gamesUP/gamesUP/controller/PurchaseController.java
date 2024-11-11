package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;  // Assure-toi que c'est le bon User

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // Endpoint pour récupérer tous les achats (Accès admin uniquement)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    // Endpoint pour récupérer les achats d'un client spécifique (Accès client uniquement)
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/user")
    public List<Purchase> getPurchasesByUser(@AuthenticationPrincipal User user) {
        // Passer le username de l'utilisateur au service pour récupérer ses achats
        return purchaseService.getPurchasesByUser(user.getUsername());
    }

    // Endpoint pour récupérer un achat par son ID
    @PreAuthorize("hasRole('CLIENT') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(id);
        return purchase.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter un nouvel achat (Accès client uniquement)
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        Purchase newPurchase = purchaseService.addPurchase(purchase);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPurchase);
    }

    // Endpoint pour mettre à jour un achat (Accès admin uniquement)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @RequestBody Purchase purchaseDetails) {
        Purchase updatedPurchase = purchaseService.updatePurchase(id, purchaseDetails);
        return updatedPurchase != null ? ResponseEntity.ok(updatedPurchase)
                                       : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer un achat (Accès admin uniquement)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
}
