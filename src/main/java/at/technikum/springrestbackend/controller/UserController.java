package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(UUID.randomUUID());
        return ResponseEntity.ok(userRepository.save(user));
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a specific user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);  // Ensure the ID is set to the one from the path
            return ResponseEntity.ok(userRepository.save(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}