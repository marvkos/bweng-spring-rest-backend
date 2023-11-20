package at.technikum.springrestbackend.controller;
import at.technikum.springrestbackend.model.Orders;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Orders> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Orders getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id);
    }
    @GetMapping("/orders/{user}")
    public List<Orders> getOrdersUsers(User user){
        return orderService.getOrdersUser(user);
    }
    @PostMapping("/orders")
    public Orders createOrder(@RequestBody Orders order){
        return orderService.createOrder(order);
    }
}
