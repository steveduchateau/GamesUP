package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.User;  // Assurez-vous que la classe User est définie
import com.gamesUP.gamesUP.model.RecommendationResponse;  // Assurez-vous que la classe RecommendationResponse est définie
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;  // Import de List

@Service
public class RecommendationService {

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("null")
    public List<Integer> getRecommendations(User user) {
        String url = "http://python-api/recommend/";

        // Envoi des données utilisateur à l'API Python pour obtenir les recommandations
        ResponseEntity<RecommendationResponse> response = restTemplate.postForEntity(url, user, RecommendationResponse.class);
        
        // Retourne les recommandations (IDs des jeux recommandés)
        return response.getBody().getRecommendations();
    }
}
