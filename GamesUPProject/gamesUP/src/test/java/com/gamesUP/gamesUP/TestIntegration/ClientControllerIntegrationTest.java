package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.DTO.ClientDTO;
import com.gamesUP.gamesUP.Service.ClientService;
import com.gamesUP.gamesUP.model.Client;
import com.gamesUP.gamesUP.Repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        // Nettoyage de la base de données avant chaque test
        clientRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnClientById_whenValidId() throws Exception {
        // Arrange: Création d'un client pour les tests
        ClientDTO clientDTO = new ClientDTO(null, "John Doe", "johndoe@example.com");
        clientDTO = clientService.saveClient(clientDTO);

        // Act & Assert: Tester l'endpoint GET /api/clients/{id}
        mockMvc.perform(get("/api/clients/{id}", clientDTO.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(clientDTO.getId().intValue())))
                .andExpect(jsonPath("$.nom", is(clientDTO.getNom())))
                .andExpect(jsonPath("$.email", is(clientDTO.getEmail())));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnAllClients_whenAdminAccess() throws Exception {
        // Arrange: Ajouter quelques clients pour tester l'endpoint
        ClientDTO clientDTO1 = new ClientDTO(null, "Jane Doe", "janedoe@example.com");
        ClientDTO clientDTO2 = new ClientDTO(null, "John Smith", "johnsmith@example.com");
        clientService.saveClient(clientDTO1);
        clientService.saveClient(clientDTO2);

        // Act & Assert: Tester l'endpoint GET /api/clients
        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is(clientDTO1.getNom())))
                .andExpect(jsonPath("$[1].nom", is(clientDTO2.getNom())));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreateNewClient_whenValidData() throws Exception {
        // Arrange: Créer un DTO pour la requête POST
        String newClientJson = "{\"nom\": \"Alice Doe\", \"email\": \"alicedoe@example.com\"}";

        // Act & Assert: Tester l'endpoint POST /api/clients
        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newClientJson))
                .andExpect(status().isCreated())  // Vérifie que le code de statut est 201
                .andExpect(jsonPath("$.nom", is("Alice Doe")))
                .andExpect(jsonPath("$.email", is("alicedoe@example.com")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldUpdateClient_whenValidIdAndData() throws Exception {
        // Arrange: Ajouter un client existant
        ClientDTO clientDTO = new ClientDTO(null, "Bob Doe", "bobdoe@example.com");
        clientDTO = clientService.saveClient(clientDTO);

        // Mettre à jour le client
        String updatedClientJson = "{\"nom\": \"Bobby Doe\", \"email\": \"bobbydoe@example.com\"}";

        // Act & Assert: Tester l'endpoint PUT /api/clients/{id}
        mockMvc.perform(put("/api/clients/{id}", clientDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedClientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom", is("Bobby Doe")))
                .andExpect(jsonPath("$.email", is("bobbydoe@example.com")));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldDeleteClient_whenValidId() throws Exception {
        // Arrange: Ajouter un client à supprimer
        ClientDTO clientDTO = new ClientDTO(null, "Tom Doe", "tomdoe@example.com");
        clientDTO = clientService.saveClient(clientDTO);

        // Act & Assert: Tester l'endpoint DELETE /api/clients/{id}
        mockMvc.perform(delete("/api/clients/{id}", clientDTO.getId()))
                .andExpect(status().isOk());

        // Vérifier si le client est supprimé de la base de données
        Optional<Client> deletedClient = clientRepository.findById(clientDTO.getId());
        assert deletedClient.isEmpty();
    }
}
