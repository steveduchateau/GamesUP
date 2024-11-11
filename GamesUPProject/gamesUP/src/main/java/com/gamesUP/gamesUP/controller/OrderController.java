package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Order;
import com.gamesUP.gamesUP.model.User;  // Importer la classe User
import com.gamesUP.gamesUP.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint pour récupérer toutes les commandes (Accès uniquement pour les Admins)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Endpoint pour récupérer une commande par son ID (Client peut voir seulement ses propres commandes)
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id, @AuthenticationPrincipal User principal) {
        // Vérifie si le client essaie d'accéder à la commande d'un autre client
        if (principal.getRole().equals("CLIENT") && !principal.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Le client n'a pas l'autorisation
        }

        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour ajouter une nouvelle commande (accessible par tous les utilisateurs authentifiés)
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @AuthenticationPrincipal User principal) {
        // Le client qui passe une commande sera lié à lui-même
        order.setUserId(principal.getId());  // Associe la commande au client
        Order newOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    // Endpoint pour mettre à jour une commande (uniquement pour les Admins)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        return updatedOrder != null ? ResponseEntity.ok(updatedOrder)
                                    : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Endpoint pour supprimer une commande (uniquement pour les Admins)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
