package com.gamesUP.gamesUP.DTO;

public class RoleDTO {
    private int id;
    private String name;

    // Constructeur sans arguments
    public RoleDTO() {}

    // Constructeur avec tous les arguments
    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
