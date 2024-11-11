package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    // Méthode personnalisée pour rechercher des auteurs par nom
    List<Author> findByNameContaining(String name); // Recherche par nom qui contient la chaîne de caractères
}
