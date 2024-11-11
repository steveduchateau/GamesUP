package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    // Endpoint pour récupérer tous les éditeurs (accessible par tous)
    @GetMapping
    public List<Publisher> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    // Endpoint pour récupérer un éditeur par son ID (accessible par tous)
    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable int id) {
        Optional<Publisher> publisher = publisherService.getPublisherById(id);
        return publisher.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter un nouvel éditeur (réservé aux administrateurs)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        Publisher newPublisher = publisherService.addPublisher(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPublisher);
    }

    // Endpoint pour mettre à jour un éditeur (réservé aux administrateurs)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id, @RequestBody Publisher publisherDetails) {
        Publisher updatedPublisher = publisherService.updatePublisher(id, publisherDetails);
        return updatedPublisher != null ? ResponseEntity.ok(updatedPublisher)
                                       : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer un éditeur (réservé aux administrateurs)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
