package at.technikum.springrestbackend.controller;
import at.technikum.springrestbackend.model.Orders;
import at.technikum.springrestbackend.model.Phone;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.OrderService;
import at.technikum.springrestbackend.service.PhoneService;
import at.technikum.springrestbackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    private final UserService userService;

    private final PhoneService phoneService;
    private final OrderService orderService;

    public OrderController(UserService userService, PhoneService phoneService, OrderService orderService) {
        this.userService = userService;
        this.phoneService = phoneService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Orders> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/{id}")
    public Orders getOrder(@PathVariable UUID id) {
        return orderService.getOrder(id);
    }
    @GetMapping("/orders/{user}")
    public List<Orders> getOrdersUsers(@PathVariable User user){
        return orderService.getOrdersUser(user);
    }


    @PostMapping("/createOrder/{username}")
    public ResponseEntity<Object> createOrder(@PathVariable @Valid String username,
            @RequestBody List<UUID> phones) {
        return handelOrderCreation(username, phones);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }

    private ResponseEntity<Object> handelOrderCreation(String username, List<UUID> phoneIds) {
        User user = userService.getUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>("No User with that username", HttpStatus.BAD_REQUEST);
        }

        if(phoneIds == null){
            return new ResponseEntity<>("At least one Phone is required", HttpStatus.BAD_REQUEST);
        }
        User mangeduser = userService.getUser(user.getId());

        Orders newOrder = new Orders(mangeduser);

        List<Phone> phones = new ArrayList<>();

        for (UUID phoneId : phoneIds) {
            Phone phone = phoneService.getPhone(phoneId);

            if (phone != null) {
                phones.add(phone);
                newOrder.addPhone(phone);
            } else {
                // Handle the case where a phone is not found for a given ID
                return new ResponseEntity<>("One of the phones was not found", HttpStatus.BAD_REQUEST);
            }
        }

        orderService.createOrder(newOrder);
        return new ResponseEntity<>("New Order is created.", HttpStatus.CREATED);
    }




}
