package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.Service.PurchaseLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase-lines")
public class PurchaseLineController {

    @Autowired
    private PurchaseLineService purchaseLineService;

    // Endpoint pour récupérer toutes les lignes d'achat - réservé à l'ADMIN
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<PurchaseLine> getAllPurchaseLines() {
        return purchaseLineService.getAllPurchaseLines();
    }

    // Endpoint pour récupérer une ligne d'achat par son ID - accessible par CLIENT ou ADMIN, mais vérification sur les données pour CLIENT
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseLine> getPurchaseLineById(@PathVariable int id) {
        Optional<PurchaseLine> purchaseLine = purchaseLineService.getPurchaseLineById(id);
        return purchaseLine.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter une nouvelle ligne d'achat - réservé à CLIENT
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<PurchaseLine> addPurchaseLine(@RequestBody PurchaseLine purchaseLine) {
        PurchaseLine newPurchaseLine = purchaseLineService.addPurchaseLine(purchaseLine);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPurchaseLine);
    }

    // Endpoint pour mettre à jour une ligne d'achat - réservé à ADMIN ou le CLIENT propriétaire de la ligne
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_CLIENT') and @purchaseLineService.isPurchaseLineOwner(authentication.name, #id))")
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseLine> updatePurchaseLine(@PathVariable int id, @RequestBody PurchaseLine purchaseLineDetails) {
        PurchaseLine updatedPurchaseLine = purchaseLineService.updatePurchaseLine(id, purchaseLineDetails);
        return updatedPurchaseLine != null ? ResponseEntity.ok(updatedPurchaseLine)
                                           : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer une ligne d'achat - réservé à ADMIN ou le CLIENT propriétaire de la ligne
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_CLIENT') and @purchaseLineService.isPurchaseLineOwner(authentication.name, #id))")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseLine(@PathVariable int id) {
        purchaseLineService.deletePurchaseLine(id);
        return ResponseEntity.noContent().build();
    }
}
