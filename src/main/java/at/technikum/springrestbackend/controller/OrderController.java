package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Order;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService brandService) {
        this.orderService = brandService;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id);
    }
    @GetMapping("/orders/{user}")
    public List<Order> getOrdersUsers(User user){
        return orderService.getOrdersUser(user);
    }
    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order){
        return orderService.createOrder(order);
    }
}
