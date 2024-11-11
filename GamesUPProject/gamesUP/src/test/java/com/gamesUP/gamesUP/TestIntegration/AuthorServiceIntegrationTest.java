package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.Service.AuthorService;
import com.gamesUP.gamesUP.Repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional  
public class AuthorServiceIntegrationTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setUp() {
        // Créer un auteur pour les tests
        author = new Author("John Doe");
        authorRepository.save(author);
    }

    @Test
    public void testAddAuthor() {
        Author newAuthor = new Author("Jane Doe");
        Author savedAuthor = authorService.addAuthor(newAuthor);

        // Vérifier si l'auteur a été bien ajouté
        Assertions.assertNotNull(savedAuthor.getId());
        Assertions.assertEquals("Jane Doe", savedAuthor.getName());
    }

    @Test
    public void testGetAllAuthors() {
        // Ajouter un autre auteur
        Author anotherAuthor = new Author("Alice Smith");
        authorService.addAuthor(anotherAuthor);

        List<Author> authors = authorService.getAllAuthors();

        // Vérifier si la taille de la liste d'auteurs est correcte
        Assertions.assertTrue(authors.size() > 1);
    }

    @Test
    public void testGetAuthorById() {
        // Récupérer l'auteur par son ID
        Optional<Author> foundAuthor = authorService.getAuthorById(author.getId());

        // Vérifier si l'auteur est bien trouvé
        Assertions.assertTrue(foundAuthor.isPresent());
        Assertions.assertEquals("John Doe", foundAuthor.get().getName());
    }

    @Test
    public void testUpdateAuthor() {
        Author updatedAuthor = new Author("John Smith");

        // Mettre à jour l'auteur existant
        Author savedAuthor = authorService.updateAuthor(author.getId(), updatedAuthor);

        // Vérifier si le nom de l'auteur a été mis à jour
        Assertions.assertEquals("John Smith", savedAuthor.getName());
    }

    @Test
    public void testDeleteAuthor() {
        Long authorId = author.getId();

        // Supprimer l'auteur
        authorService.deleteAuthor(authorId);

        // Vérifier si l'auteur a été supprimé
        Optional<Author> deletedAuthor = authorService.getAuthorById(authorId);
        Assertions.assertFalse(deletedAuthor.isPresent());
    }

    @Test
    public void testSearchAuthors() {
        // Ajouter un autre auteur
        Author anotherAuthor = new Author("Jane Smith");
        authorService.addAuthor(anotherAuthor);

        // Recherche d'un auteur par son nom
        List<Author> authors = authorService.searchAuthors("Jane");

        // Vérifier si les auteurs correspondant à la recherche sont trouvés
        Assertions.assertTrue(authors.size() > 0);
        Assertions.assertTrue(authors.stream().anyMatch(a -> a.getName().contains("Jane")));
    }
}
