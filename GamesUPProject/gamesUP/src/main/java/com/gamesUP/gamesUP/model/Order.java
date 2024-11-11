package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "game_id", nullable = false)
    private Long gameId;

    @Column(name = "user_id")  // Ajout de la colonne user_id
    private Long userId;  // Champ pour stocker l'ID de l'utilisateur

    @Column(name = "order_date", nullable = false)
    private Date orderDate;  // Utilisation de java.util.Date

    @Column(name = "status", nullable = false)
    private String status;

    // Relation Many-to-One avec Client
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Constructeur par défaut
    public Order() {}

    // Constructeur avec paramètres
    public Order(Long gameId, Long userId, Date orderDate, String status, Client client) {
        this.gameId = gameId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.client = client;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // Implémentation de equals() et hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(gameId, order.gameId) &&
               Objects.equals(userId, order.userId) &&
               Objects.equals(orderDate, order.orderDate) &&
               Objects.equals(status, order.status) &&
               Objects.equals(client, order.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, userId, orderDate, status, client);
    }
}
