package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.DTO.ClientDTO;
import com.gamesUP.gamesUP.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Endpoint pour récupérer un client par son ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")  // Permet l'accès aux admins et au client authentifié
    public ClientDTO getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);  // Le service renvoie déjà un Client
    }

    // Endpoint pour récupérer tous les clients (réservé aux admins)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")  // Restreint l'accès aux administrateurs uniquement
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();  // Le service renvoie déjà une liste de Clients
    }

    // Endpoint pour créer un nouveau client
    @PostMapping
    @PreAuthorize("permitAll()")  // Ouvert à tous pour l'inscription
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        ClientDTO createdClient = clientService.saveClient(client);  // Le service renvoie un Client après la sauvegarde
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);  // Retourne un code HTTP 201 et le client créé
    }

    // Endpoint pour mettre à jour un client existant
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")  // Permet la mise à jour pour l'admin ou le client authentifié
    public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO updatedClient) {
        return clientService.updateClient(id, updatedClient);  // Le service renvoie un Client après la mise à jour
    }

    // Endpoint pour supprimer un client (réservé aux admins)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")  // Restreint l'accès à l'administrateur uniquement
    public boolean deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);  // Le service gère la suppression, retourne true ou false
    }
}
