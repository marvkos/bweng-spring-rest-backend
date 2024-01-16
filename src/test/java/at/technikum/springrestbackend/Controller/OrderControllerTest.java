package at.technikum.springrestbackend.Controller;


import at.technikum.springrestbackend.controller.OrderController;
import at.technikum.springrestbackend.model.Orders;
import at.technikum.springrestbackend.model.Phone;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.security.jwt.JwtToPrincipalConverter;
import at.technikum.springrestbackend.service.OrderService;
import at.technikum.springrestbackend.service.PhoneService;
import at.technikum.springrestbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PhoneService phoneService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;
    @Test
    void testGetOrders() {
        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
        when(orderService.getOrders()).thenReturn(mockOrders);

        List<Orders> result = orderController.getOrders();

        assertEquals(mockOrders, result);
    }

    @Test
    void testGetOrder_Found() {
        UUID id = UUID.randomUUID();
        Orders mockOrder = new Orders();
        when(orderService.getOrder(id)).thenReturn(mockOrder);

        Orders result = orderController.getOrder(id);

        assertEquals(mockOrder, result);
    }

    @Test
    void testGetOrder_NotFound() {
        UUID id = UUID.randomUUID();
        when(orderService.getOrder(id)).thenReturn(null);

        Orders result = orderController.getOrder(id);

        assertNull(result);
    }

    @Test
    void testGetOrdersUsers() {
        User user = new User();
        List<Orders> mockOrders = Arrays.asList(new Orders(), new Orders());
        when(orderService.getOrdersUser(user)).thenReturn(mockOrders);

        List<Orders> result = orderController.getOrdersUsers(user);

        assertEquals(mockOrders, result);
    }

    @Test
    void testCreateOrder_Success() {        String username = "user1";
        User user = new User();
        user.setUsername(username);
        when(userService.getUserByUsername(username)).thenReturn(user);

        List<UUID> phoneIds = Arrays.asList(UUID.randomUUID(), UUID.randomUUID());
        when(phoneService.getPhone(any(UUID.class))).thenReturn(new Phone());

        ResponseEntity<Object> response = orderController.createOrder(username, phoneIds);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Order is created.", response.getBody());
    }

    @Test
    void testCreateOrder_UserNotFound() {
        String username = "nonexistent_user";
        when(userService.getUserByUsername(username)).thenReturn(null);

        ResponseEntity<Object> response = orderController.createOrder(username, Arrays.asList(UUID.randomUUID()));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("No User with that username", response.getBody());
    }


    @Test
    void testDeleteOrder_Success() {
        UUID orderId = UUID.randomUUID();
        Orders mockOrder = new Orders();
        User mockUser = new User();
        mockUser.setUsername("testUser");
        mockOrder.setUser(mockUser);

        when(orderService.getOrder(orderId)).thenReturn(mockOrder);

        try (MockedStatic<JwtToPrincipalConverter> mocked = Mockito.mockStatic(JwtToPrincipalConverter.class)) {
            mocked.when(JwtToPrincipalConverter::getCurrentUsername).thenReturn("testUser");
            mocked.when(JwtToPrincipalConverter::getCurrentUserRole).thenReturn("ROLE_admin");

            ResponseEntity<String> response = orderController.deleteOrder(orderId);

            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Order deleted successfully", response.getBody());
        }
    }

    @Test
    void testDeleteOrder_NotFound() {
        UUID orderId = UUID.randomUUID();
        when(orderService.getOrder(orderId)).thenReturn(null);

        ResponseEntity<String> response = orderController.deleteOrder(orderId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("There is no Order with this ID", response.getBody());
    }

}