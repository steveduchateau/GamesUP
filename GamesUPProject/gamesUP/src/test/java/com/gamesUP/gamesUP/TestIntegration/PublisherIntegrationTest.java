package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.Repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PublisherRepository publisherRepository;

    @BeforeEach
    void setup() {
        publisherRepository.deleteAll();  // On s'assure qu'il n'y a pas de données avant chaque test
    }

    @Test
    void testGetAllPublishers() throws Exception {
        // Ajouter un éditeur pour tester la récupération
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        publisherRepository.save(publisher);

        // Test GET /api/publishers
        mockMvc.perform(get("/api/publishers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Publisher"));
    }

    @Test
    void testGetPublisherById() throws Exception {
        // Ajouter un éditeur
        Publisher publisher = new Publisher();
        publisher.setName("Test Publisher");
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Test GET /api/publishers/{id}
        mockMvc.perform(get("/api/publishers/{id}", savedPublisher.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Publisher"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")  // Authentification avec le rôle ADMIN
    void testAddPublisher() throws Exception {
        Publisher publisher = new Publisher();
        publisher.setName("New Publisher");

        // Test POST /api/publishers
        mockMvc.perform(post("/api/publishers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"New Publisher\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Publisher"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")  // Authentification avec le rôle ADMIN
    void testUpdatePublisher() throws Exception {
        // Ajouter un éditeur
        Publisher publisher = new Publisher();
        publisher.setName("Old Publisher");
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Test PUT /api/publishers/{id}
        mockMvc.perform(put("/api/publishers/{id}", savedPublisher.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Publisher\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Publisher"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")  // Authentification avec le rôle ADMIN
    void testDeletePublisher() throws Exception {
        // Ajouter un éditeur
        Publisher publisher = new Publisher();
        publisher.setName("Publisher to Delete");
        Publisher savedPublisher = publisherRepository.save(publisher);

        // Test DELETE /api/publishers/{id}
        mockMvc.perform(delete("/api/publishers/{id}", savedPublisher.getId()))
                .andExpect(status().isNoContent());

        // Vérifier que l'éditeur a bien été supprimé
        mockMvc.perform(get("/api/publishers/{id}", savedPublisher.getId()))
                .andExpect(status().isNotFound());
    }
}
