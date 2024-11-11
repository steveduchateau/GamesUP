package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Méthode de recherche d'un rôle par son nom, qui retourne un Optional<Role>
    Optional<Role> findByName(String name);
}
