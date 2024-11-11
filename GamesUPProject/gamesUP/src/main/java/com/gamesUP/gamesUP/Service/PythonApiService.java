package com.gamesUP.gamesUP.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PythonApiService {

    @Autowired
    private RestTemplate restTemplate;

    // URL de votre API Python
    private static final String PYTHON_API_URL = "http://localhost:8000/data";

    public Object getPythonData() {
        // Effectue une requête GET vers l'API Python
        return restTemplate.getForObject(PYTHON_API_URL, Object.class);
    }
}
