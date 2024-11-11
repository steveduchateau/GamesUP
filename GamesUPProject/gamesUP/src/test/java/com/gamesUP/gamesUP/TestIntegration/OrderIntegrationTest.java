package com.gamesUP.gamesUP.TestIntegration;

import com.gamesUP.gamesUP.Service.OrderService;
import com.gamesUP.gamesUP.controller.OrderController;
import com.gamesUP.gamesUP.model.Order;
import com.gamesUP.gamesUP.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OrderIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private Order order;
    private Client client;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        // Initialisation de l'objet Client
        client = new Client(1L, "Nom Client", "client@example.com");

        // Initialisation de l'objet Order
        order = new Order(1L, 1L, new Date(), "Pending", client);
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAllOrdersAsAdmin() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(order));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("Pending"));
    }

    @Test
    @WithMockUser(username = "client", roles = {"CLIENT"})
    public void testAddOrder() throws Exception {
        when(orderService.addOrder(any(Order.class))).thenAnswer(invocation -> {
            Order createdOrder = invocation.getArgument(0);
            createdOrder.setId(1L);
            createdOrder.setStatus("Pending");
            return createdOrder;
        });

        mockMvc.perform(post("/api/orders")
                .contentType("application/json")
                .content("{\"status\":\"Pending\", \"gameId\":1, \"userId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUpdateOrderAsAdmin() throws Exception {
        when(orderService.updateOrder(eq(1L), any(Order.class))).thenReturn(order);

        mockMvc.perform(put("/api/orders/1")
                .contentType("application/json")
                .content("{\"status\":\"Shipped\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteOrderAsAdmin() throws Exception {
        doNothing().when(orderService).deleteOrder(1L);

        mockMvc.perform(delete("/api/orders/1"))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(1L);
    }
}
