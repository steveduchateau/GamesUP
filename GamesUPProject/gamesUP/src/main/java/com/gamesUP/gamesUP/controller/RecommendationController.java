package com.gamesUP.gamesUP.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RecommendationController {

    private final RestTemplate restTemplate;

    public RecommendationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/getRecommendation")
    public ResponseEntity<Object> getRecommendation(@RequestParam int userId, @RequestParam int gameId) {
        // URL de l'API Python
        String url = "http://localhost:8000/recommend?user_id=" + userId + "&game_id=" + gameId;

        try {
            // Effectuer la requête GET vers l'API Python
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return response;
        } catch (Exception e) {
            // Si une erreur se produit lors de l'appel à l'API Python
            return ResponseEntity.status(500).body("Erreur lors de l'appel à l'API Python : " + e.getMessage());
        }
    }
}
