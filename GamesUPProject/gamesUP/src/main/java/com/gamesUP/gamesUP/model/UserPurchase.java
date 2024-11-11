package com.gamesUP.gamesUP.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserPurchase {

    @Id
    private int id;
    private int userId;
    private int gameId;
    private float rating;

    // Constructeurs, getters et setters
    public UserPurchase() {}

    public UserPurchase(int id, int userId, int gameId, float rating) {
        this.id = id;
        this.userId = userId;
        this.gameId = gameId;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
