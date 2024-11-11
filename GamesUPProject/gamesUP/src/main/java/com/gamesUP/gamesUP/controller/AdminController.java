package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.DTO.ClientDTO;
import com.gamesUP.gamesUP.Service.ClientService;
import com.gamesUP.gamesUP.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.gamesUP.gamesUP.model.Order;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private OrderService orderService;

    // Accès réservé à l'admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/clients")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    // Accès réservé à l'admin
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/commandes")
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();
    }
}
