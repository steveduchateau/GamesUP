package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private CustomUserDetailsService customUserService; // Service pour récupérer l'ID de l'utilisateur

    // Méthode pour récupérer tous les achats
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    // Méthode pour récupérer un achat par son ID
    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    // Méthode pour ajouter un nouvel achat
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    // Méthode pour mettre à jour un achat
    public Purchase updatePurchase(Long id, Purchase purchaseDetails) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        if (purchaseOptional.isPresent()) {
            Purchase purchase = purchaseOptional.get();
            purchase.setLine(purchaseDetails.getLine());
            purchase.setDate(purchaseDetails.getDate());
            purchase.setPaid(purchaseDetails.isPaid());
            purchase.setDelivered(purchaseDetails.isDelivered());
            purchase.setArchived(purchaseDetails.isArchived());
            return purchaseRepository.save(purchase);
        }
        return null;
    }

    // Méthode pour supprimer un achat
    public void deletePurchase(Long id) {
        purchaseRepository.deleteById(id);
    }

    // Méthode pour récupérer les achats d'un utilisateur par son username
    public List<Purchase> getPurchasesByUser(String username) {
        // Utiliser le service CustomUserService pour obtenir l'ID de l'utilisateur à partir de son username
        Long userId = customUserService.getUserIdByUsername(username);

        // Récupérer les achats de l'utilisateur avec son ID
        return purchaseRepository.findByUserId(userId);
    }
}
