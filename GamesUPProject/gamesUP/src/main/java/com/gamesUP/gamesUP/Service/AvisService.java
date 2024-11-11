package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.Repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvisService {

    @Autowired
    private AvisRepository avisRepository;

    // Méthode pour récupérer tous les avis
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }

    // Méthode pour récupérer un avis par son ID
    public Optional<Avis> getAvisById(Long id) {
        return avisRepository.findById(id);
    }

    // Méthode pour ajouter un nouvel avis
    public Avis addAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    // Méthode pour mettre à jour un avis existant
    public Avis updateAvis(Long id, Avis avisDetails) {
        Optional<Avis> avisOptional = avisRepository.findById(id);
        if (avisOptional.isPresent()) {
            Avis avis = avisOptional.get();
            avis.setCommentaire(avisDetails.getCommentaire());
            avis.setNote(avisDetails.getNote());
            return avisRepository.save(avis);
        } else {
            // Avis non trouvé
            return null;
        }
    }

    // Méthode pour supprimer un avis par son ID
    public void deleteAvis(Long id) {
        avisRepository.deleteById(id);
    }

    // Méthode pour vérifier si un avis appartient à l'utilisateur connecté
    public boolean isAvisOwner(Long avisId, Object principal) {
        Optional<Avis> avis = avisRepository.findById(avisId);
        if (avis.isPresent()) {
            // Vérification si l'utilisateur connecté est le propriétaire de l'avis
            User user = (User) principal; // Récupérer l'utilisateur authentifié
            return avis.get().getUser().getUsername().equals(user.getUsername());
        }
        return false;
    }
}
