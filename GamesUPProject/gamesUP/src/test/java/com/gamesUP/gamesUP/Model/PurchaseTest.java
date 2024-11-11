package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.model.PurchaseLine;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseTest {

    private Purchase purchase;

    @BeforeEach
    public void setUp() {
        // Initialisation d'un objet Purchase avant chaque test
        PurchaseLine line1 = new PurchaseLine(); // Ajoutez des détails pour PurchaseLine si nécessaire
        PurchaseLine line2 = new PurchaseLine(); // Ajoutez des détails pour PurchaseLine si nécessaire
        purchase = new Purchase(
                Arrays.asList(line1, line2),
                new Date(),
                true, // paid
                false, // delivered
                false, // archived
                123L // userId
        );
    }

    @Test
    public void testGettersAndSetters() {
        // Vérification des getters et setters
        assertNotNull(purchase.getLine());
        assertEquals(2, purchase.getLine().size());
        assertNotNull(purchase.getDate());
        assertTrue(purchase.isPaid());
        assertFalse(purchase.isDelivered());
        assertFalse(purchase.isArchived());
        assertEquals(123L, purchase.getUserId());
    }

    @Test
    public void testConstructorWithParams() {
        // Test du constructeur avec paramètres
        PurchaseLine line1 = new PurchaseLine();
        Date date = new Date();
        Purchase purchase = new Purchase(Arrays.asList(line1), date, true, false, false, 456L);

        assertEquals(1, purchase.getLine().size());
        assertEquals(date, purchase.getDate());
        assertTrue(purchase.isPaid());
        assertFalse(purchase.isDelivered());
        assertFalse(purchase.isArchived());
        assertEquals(456L, purchase.getUserId());
    }

    @Test
    public void testDefaultConstructor() {
        // Test du constructeur par défaut
        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setPaid(true);
        purchase.setDelivered(false);
        purchase.setArchived(false);
        purchase.setUserId(123L);

        assertEquals(1L, purchase.getId());
        assertTrue(purchase.isPaid());
        assertFalse(purchase.isDelivered());
        assertFalse(purchase.isArchived());
        assertEquals(123L, purchase.getUserId());
    }
}
