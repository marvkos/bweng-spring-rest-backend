package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.LoginRequestDTO;
import at.technikum.springrestbackend.dto.LoginResponseDTO;
import at.technikum.springrestbackend.dto.UserDTO;
import at.technikum.springrestbackend.mapper.UserMapper;
import at.technikum.springrestbackend.services.AuthenticationServices;
import at.technikum.springrestbackend.services.UserServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "Authentication", description = "API für Authentifizierung und Registrierung von Benutzern")
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class Authentication {

    private final AuthenticationServices authenticationService;
    private final UserMapper userMapper;
    private final UserServices userServices;

    @Autowired
    public Authentication(AuthenticationServices authenticationService, UserMapper userMapper, UserServices userServices) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.userServices = userServices;
    }

    @Operation(summary = "Erstellt ein JWT und speichert es in einem HTTP-Only Cookie")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestDTO loginRequestDTO, HttpServletResponse response) {
        try {
            LoginResponseDTO responseDTO = authenticationService.login(loginRequestDTO);

            // JWT-Token in einem HTTP-Only Cookie speichern
            Cookie cookie = new Cookie("jwt", responseDTO.getToken());
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60); // Token für 1 Tag gültig
            response.addCookie(cookie);

            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @Operation(summary = "Registriert einen neuen Benutzer")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO) {
        try {
            UserDTO createdUser = userServices.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (EntityExistsException e) {
            // Handle the case where the username or email already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @Operation(summary = "Löscht das JWT aus dem Cookie, um den Benutzer auszuloggen")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // JWT-Cookie löschen
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok("Logout erfolgreich");
    }
}
