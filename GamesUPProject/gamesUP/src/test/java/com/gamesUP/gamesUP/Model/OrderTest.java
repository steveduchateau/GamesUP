package com.gamesUP.gamesUP.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gamesUP.gamesUP.model.Client;
import com.gamesUP.gamesUP.model.Order;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order order;
    private Client client;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets avant chaque test
        client = new Client();  // Vous devez remplacer Client par la classe Client correcte
        client.setId(1L);  // Exemple de client
        order = new Order(123L, 456L, new Date(), "PENDING", client);
    }

    @Test
    public void testConstructorAndGetters() {
        // Vérifier que l'objet Order est bien créé avec le bon constructeur
        assertNotNull(order);
        assertEquals(123L, order.getGameId());
        assertEquals(456L, order.getUserId());
        assertNotNull(order.getOrderDate());  // Assurez-vous que la date n'est pas nulle
        assertEquals("PENDING", order.getStatus());
        assertEquals(client, order.getClient());
    }

    @Test
    public void testSetters() {
        // Vérifier que les setters fonctionnent correctement
        order.setGameId(789L);
        order.setUserId(101L);
        order.setOrderDate(new Date());
        order.setStatus("COMPLETED");
        order.setClient(client);

        assertEquals(789L, order.getGameId());
        assertEquals(101L, order.getUserId());
        assertNotNull(order.getOrderDate());
        assertEquals("COMPLETED", order.getStatus());
        assertEquals(client, order.getClient());
    }

    @Test
    public void testOrderEquality() {
        // Tester l'égalité entre deux objets Order (basé sur les IDs et les attributs)
        Order anotherOrder = new Order(123L, 456L, new Date(), "PENDING", client);
        assertEquals(order, anotherOrder);
    }

    @Test
    public void testOrderNotEqual() {
        // Tester l'inégalité entre deux objets Order avec des IDs différents
        Order differentOrder = new Order(999L, 456L, new Date(), "PENDING", client);
        assertNotEquals(order, differentOrder);
    }

    @Test
    public void testDefaultConstructor() {
        // Vérifier que le constructeur par défaut crée un objet Order valide
        Order defaultOrder = new Order();
        assertNotNull(defaultOrder);
        assertNull(defaultOrder.getGameId());
        assertNull(defaultOrder.getUserId());
        assertNull(defaultOrder.getOrderDate());
        assertNull(defaultOrder.getStatus());
        assertNull(defaultOrder.getClient());
    }
}
