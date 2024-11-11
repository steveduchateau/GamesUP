package com.gamesUP.gamesUP.Service;

import com.gamesUP.gamesUP.model.Order;
import com.gamesUP.gamesUP.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Méthode pour récupérer toutes les commandes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Méthode pour récupérer une commande par son ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Méthode pour ajouter une nouvelle commande
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    // Méthode pour mettre à jour une commande
    public Order updateOrder(Long id, Order orderDetails) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setUserId(orderDetails.getUserId());  // Correction: Utilisation de setUserId
            order.setGameId(orderDetails.getGameId());
            order.setOrderDate(orderDetails.getOrderDate());
            order.setStatus(orderDetails.getStatus());
            return orderRepository.save(order);
        }
        return null;
    }

    // Méthode pour supprimer une commande
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
