package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.DTO.ClientDTO;  // Assurez-vous que la classe ClientDTO est dans le package DTO
import com.gamesUP.gamesUP.model.Client;  // Assurez-vous que la classe Client est dans le package model
import com.gamesUP.gamesUP.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Méthode pour obtenir un client par son ID
    public ClientDTO getClientById(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        // Convertir Client en ClientDTO
        return clientOpt.map(this::convertToDTO).orElse(null);  // Ou gérer autrement selon le cas
    }

    // Méthode pour obtenir tous les clients
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        // Convertir la liste des clients en une liste de DTOs
        return clients.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Méthode pour sauvegarder un client
    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) {
        // Convertir le DTO en entité Client
        Client client = convertToEntity(clientDTO);
        // Sauvegarder l'entité Client
        Client savedClient = clientRepository.save(client);
        // Convertir l'entité sauvegardée en DTO
        return convertToDTO(savedClient);
    }

    // Méthode pour mettre à jour un client existant
    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO updatedClientDTO) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            // Mettre à jour les propriétés de l'entité
            client.setNom(updatedClientDTO.getNom());
            client.setEmail(updatedClientDTO.getEmail());
            // Sauvegarder l'entité mise à jour
            Client updatedClient = clientRepository.save(client);
            // Convertir l'entité mise à jour en DTO
            return convertToDTO(updatedClient);
        } else {
            // Option : Retourner null ou lever une exception spécifique (ex : ClientNotFoundException)
            return null;
        }
    }

    // Méthode pour supprimer un client
    @Transactional
    public boolean deleteClient(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            clientRepository.deleteById(id);
            return true; // Client supprimé avec succès
        } else {
            return false; // Client non trouvé
        }
    }

    // Convertir un Client en ClientDTO
    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(client.getId(), client.getNom(), client.getEmail());
    }

    // Convertir un ClientDTO en Client
    private Client convertToEntity(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getNom(), clientDTO.getEmail());
    }
}
