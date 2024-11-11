package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.PurchaseLine;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseLineTest {

    @Test
    void testPurchaseLineConstructor() {
        PurchaseLine purchaseLine = new PurchaseLine(1, 101, 202, 29.99);
        assertEquals(1, purchaseLine.getId());
        assertEquals(101, purchaseLine.getUtilisateurId());
        assertEquals(202, purchaseLine.getJeuId());
        assertEquals(29.99, purchaseLine.getPrix());
    }

    @Test
    void testSettersAndGetters() {
        PurchaseLine purchaseLine = new PurchaseLine();
        purchaseLine.setId(1);
        purchaseLine.setUtilisateurId(101);
        purchaseLine.setJeuId(202);
        purchaseLine.setPrix(29.99);

        assertEquals(1, purchaseLine.getId());
        assertEquals(101, purchaseLine.getUtilisateurId());
        assertEquals(202, purchaseLine.getJeuId());
        assertEquals(29.99, purchaseLine.getPrix());
    }

    @Test
    void testToString() {
        PurchaseLine purchaseLine = new PurchaseLine(1, 101, 202, 29.99);
        String expected = "PurchaseLine{id=1, utilisateurId=101, jeuId=202, prix=29.99}";
        assertEquals(expected, purchaseLine.toString());
    }
}
