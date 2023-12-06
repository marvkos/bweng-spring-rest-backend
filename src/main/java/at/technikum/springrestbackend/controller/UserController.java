package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Create a new user
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get all users
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a specific user by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.User', 'update') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.User', 'update') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }

    // Update a user by ID
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    // Update only the username
    @PatchMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.User', 'update')")
    public User updateUsername(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUsername(id, user);
    }

}
