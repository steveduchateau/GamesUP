package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Rechercher un utilisateur par son nom
    Optional<User> findByNom(String nom);

    // Rechercher un utilisateur par son nom d'utilisateur
    Optional<User> findByUsername(String username);
}
