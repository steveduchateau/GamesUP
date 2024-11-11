package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.Service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    // Endpoint pour récupérer tous les avis - réservé aux ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Avis> getAllAvis() {
        return avisService.getAllAvis();
    }

    // Endpoint pour récupérer un avis par son ID - accessible par tous
    @GetMapping("/{id}")
    public ResponseEntity<Avis> getAvisById(@PathVariable Long id) {
        Optional<Avis> avis = avisService.getAvisById(id);
        return avis.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter un nouvel avis - réservé aux CLIENT
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<Avis> addAvis(@RequestBody Avis avis) {
        Avis newAvis = avisService.addAvis(avis);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAvis);
    }

    // Endpoint pour mettre à jour un avis existant - réservé au propriétaire de l'avis ou à l'ADMIN
    @PreAuthorize("hasRole('CLIENT') and @avisService.isAvisOwner(#id, principal) or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Avis> updateAvis(@PathVariable Long id, @RequestBody Avis avisDetails) {
        Avis updatedAvis = avisService.updateAvis(id, avisDetails);
        return updatedAvis != null ? ResponseEntity.ok(updatedAvis) :
                                     ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer un avis par son ID - réservé au propriétaire de l'avis ou à l'ADMIN
    @PreAuthorize("hasRole('CLIENT') and @avisService.isAvisOwner(#id, principal) or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        avisService.deleteAvis(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
