package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.Repository.PurchaseLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseLineService {

    @Autowired
    private PurchaseLineRepository purchaseLineRepository;

    // Méthode pour récupérer toutes les lignes d'achat
    public List<PurchaseLine> getAllPurchaseLines() {
        return purchaseLineRepository.findAll();
    }

    // Méthode pour récupérer une ligne d'achat par son ID
    public Optional<PurchaseLine> getPurchaseLineById(int id) {
        return purchaseLineRepository.findById(id);
    }

    // Méthode pour ajouter une nouvelle ligne d'achat
    public PurchaseLine addPurchaseLine(PurchaseLine purchaseLine) {
        return purchaseLineRepository.save(purchaseLine);
    }

    // Méthode pour mettre à jour une ligne d'achat
    public PurchaseLine updatePurchaseLine(int id, PurchaseLine purchaseLineDetails) {
        Optional<PurchaseLine> purchaseLineOptional = purchaseLineRepository.findById(id);
        if (purchaseLineOptional.isPresent()) {
            PurchaseLine purchaseLine = purchaseLineOptional.get();
            purchaseLine.setUtilisateurId(purchaseLineDetails.getUtilisateurId());
            purchaseLine.setJeuId(purchaseLineDetails.getJeuId());
            purchaseLine.setPrix(purchaseLineDetails.getPrix());
            return purchaseLineRepository.save(purchaseLine);
        }
        return null;
    }

    // Méthode pour supprimer une ligne d'achat
    public void deletePurchaseLine(int id) {
        purchaseLineRepository.deleteById(id);
    }
}
