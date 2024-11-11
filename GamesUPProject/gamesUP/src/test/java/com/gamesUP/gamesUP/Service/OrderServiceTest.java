package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.Repository.OrderRepository;
import com.gamesUP.gamesUP.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;  // Mock du repository

    @InjectMocks
    private OrderService orderService;  // Service avec l'injection des mocks

    private Order order;

    @BeforeEach
    public void setUp() throws ParseException {
        // Initialisation d'une commande de test
        order = new Order();
        order.setId(1L);
        order.setUserId(123L);
        order.setGameId(456L);

        // Conversion de la chaîne en objet Date
        String orderDateString = "2024-11-10";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = sdf.parse(orderDateString);
        order.setOrderDate(orderDate);

        order.setStatus("Pending");
    }

    @Test
    public void testAddOrder() {
        // Comportement du mock pour la méthode save
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.addOrder(order);

        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getId());
        assertEquals("Pending", createdOrder.getStatus());
    }

    @Test
    public void testGetOrderById() {
        // Comportement du mock pour la méthode findById
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById(1L);

        assertTrue(foundOrder.isPresent());
        assertEquals(1L, foundOrder.get().getId());
    }

    @Test
    public void testUpdateOrder() throws ParseException {
        // Comportement du mock pour findById
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Création d'une commande mise à jour
        Order updatedOrder = new Order();
        updatedOrder.setUserId(456L);
        updatedOrder.setGameId(789L);

        // Conversion de la chaîne en Date
        String orderDateString = "2024-11-11";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = sdf.parse(orderDateString);
        updatedOrder.setOrderDate(orderDate);

        updatedOrder.setStatus("Completed");

        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(updatedOrder);

        Order result = orderService.updateOrder(1L, updatedOrder);

        assertNotNull(result);
        assertEquals("Completed", result.getStatus());
        assertEquals(456L, result.getUserId());
    }

    @Test
    public void testDeleteOrder() {
        // Nous mockons simplement la méthode deleteById
        Mockito.doNothing().when(orderRepository).deleteById(1L);

        orderService.deleteOrder(1L);

        // Vérification que la méthode a été appelée une fois
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(1L);
    }
}
