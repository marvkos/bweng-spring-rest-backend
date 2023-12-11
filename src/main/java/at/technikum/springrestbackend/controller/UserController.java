package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.UserService;
import at.technikum.springrestbackend.util.UserValidator;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/userid/{id}")
    public User getUser(UUID id) {
        return userService.getUser(id);
    }

    @GetMapping("/users/username/{username}")
    public User getUserUserName(String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/users/role/{role}")
    public List<User> getUsersRole(int role) {
        return userService.getUsersRole(role);
    }

    @GetMapping("/users/firstname/{firstname}")
    public List<User> getUsersFirstname(String firstname) {
        return userService.getUsersFirstname(firstname);
    }

    @GetMapping("/users/lastname/{lastname}")
    public List<User> getUsersLastname(String lastname) {
        return userService.getUsersLastname(lastname);
    }

    @GetMapping("/users/email/{email}")
    public User getUserEmail(String email) {
        return userService.getUserEmail(email);
    }

    @GetMapping("/users/country/{country}")
    public List<User> getUsersCountry(String country) {
        return userService.getUsersCountry(country);
    }

    @GetMapping("/user/helloWorld")
    public String helloWorld(){
        return "Hello World";
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid User user) {
        return handleUserCreation(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        User userToDelete = userService.getUser(id);
        return handleUserDeletion(userToDelete);
    }

    @PutMapping("/updateUser/{name}")
    public ResponseEntity<Object> updateUser(@PathVariable String name, @RequestBody @Valid User updatedUser) {
        return handleUserUpdate(name, updatedUser);
    }

    private ResponseEntity<Object> handleUserCreation(User user) {
        List<String> validationErrors = userValidator.validateUserRegistration(user);
        if (!validationErrors.isEmpty()) {
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    private ResponseEntity<Object> handleUserDeletion(User userToDelete) {
        if (userToDelete == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(userToDelete.getId());
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    private ResponseEntity<Object> handleUserUpdate(String name, User updatedUser) {
        int affectedRows = userService.updateUserInfo(name, updatedUser.getUsername(), updatedUser.getPassword(),updatedUser.getRole(),updatedUser.getFirstname(),updatedUser.getLastname(),updatedUser.getSalutation(),updatedUser.getEmail(),updatedUser.getStreet(),updatedUser.getHauseNumber(),updatedUser.getFlatNumber(),updatedUser.getCity(),updatedUser.getPostalcode(),updatedUser.getCountry(),updatedUser.getProfilePicture());

        if (affectedRows > 0) {
            return new ResponseEntity<>("User info has been updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
