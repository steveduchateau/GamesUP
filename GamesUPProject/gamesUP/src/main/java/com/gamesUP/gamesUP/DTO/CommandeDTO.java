package com.gamesUP.gamesUP.DTO;

public class CommandeDTO {

    private Long id;
    private String status;
    private Double total;
    private Long clientId; // ID du client associé à cette commande

    // Constructors, Getters and Setters
    public CommandeDTO(Long id, String status, Double total, Long clientId) {
        this.id = id;
        this.status = status;
        this.total = total;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
