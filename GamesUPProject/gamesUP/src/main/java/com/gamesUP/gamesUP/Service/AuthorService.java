package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    // Récupérer tous les auteurs
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Ajouter un nouvel auteur
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Récupérer un auteur par son ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // Mettre à jour un auteur
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found for this id :: " + id));

        author.setName(authorDetails.getName());
        return authorRepository.save(author);
    }

    // Supprimer un auteur
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found for this id :: " + id));

        authorRepository.delete(author);
    }

    // Recherche des auteurs par nom
    public List<Author> searchAuthors(String query) {
        return authorRepository.findByNameContaining(query);  // Recherche par nom
    }
}
