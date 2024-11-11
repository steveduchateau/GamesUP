package com.gamesUP.gamesUP.Repository;

import com.gamesUP.gamesUP.model.Client;  // Assurez-vous d'importer la classe Client du package model
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Méthode pour trouver un client par son email
    Optional<Client> findByEmail(String email);

    // Vous pouvez ajouter d'autres méthodes personnalisées selon les besoins
}
