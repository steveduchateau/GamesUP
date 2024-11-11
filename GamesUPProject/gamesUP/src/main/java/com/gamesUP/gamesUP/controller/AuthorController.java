package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // Endpoint pour récupérer tous les auteurs
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // Endpoint pour ajouter un auteur - uniquement accessible par un Admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    // Endpoint pour récupérer un auteur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable(value = "id") Long authorId) {
        Optional<Author> author = authorService.getAuthorById(authorId);
        if (author.isPresent()) {
            return ResponseEntity.ok().body(author.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint pour mettre à jour un auteur - uniquement accessible par un Admin
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable(value = "id") Long authorId,
                                               @RequestBody Author authorDetails) {
        Author updatedAuthor = authorService.updateAuthor(authorId, authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    // Endpoint pour supprimer un auteur - uniquement accessible par un Admin
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable(value = "id") Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour rechercher des auteurs par nom ou autre critère
    @GetMapping("/search")
    public List<Author> searchAuthors(@RequestParam String query) {
        return authorService.searchAuthors(query);
    }
}
