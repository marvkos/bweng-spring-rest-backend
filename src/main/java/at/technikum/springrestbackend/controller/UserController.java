package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.service.UserService;
import at.technikum.springrestbackend.util.PasswordValidator;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

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
        return userService.getUserUsername(username);
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
        List<String> validationErrors = validateUserRegistration(user);
        if (!validationErrors.isEmpty()) {
            return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
    @DeleteMapping("/users/userid/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        User userToDelete = userService.getUser(id);

        if (userToDelete == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }



    private List<String> validateUserRegistration(User user) {
        List<String> validationErrors = new ArrayList<>();

        if (userService.isUsernameTaken(user.getUsername())) {
            validationErrors.add("Username is already taken");
        }
        if (userService.isEmailTaken(user.getEmail())) {
            validationErrors.add("Email is already taken");
        }
        if (!PasswordValidator.isValidPassword(user.getPassword())) {
            validationErrors.add("Invalid password");
        }

        return validationErrors;
    }
}

