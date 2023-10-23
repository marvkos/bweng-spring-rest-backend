package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.Order;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(UUID id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getOrdersUser(User user) {
        return orderRepository.findByUser(user);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
