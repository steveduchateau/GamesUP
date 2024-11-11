package com.gamesUP.gamesUP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private com.gamesUP.gamesUP.Service.PythonApiService pythonApiService;

    // Endpoint qui renvoie les données récupérées depuis l'API Python
    @GetMapping("/python-data")
    public Object getPythonData() {
        return pythonApiService.getPythonData();
    }
}
