package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.TokenRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import at.technikum.springrestbackend.dto.TokenResponse;
import at.technikum.springrestbackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authservice;
     public AuthController(AuthService authservice){
         this.authservice = authservice;
     }
     @PostMapping("/token")
    public TokenResponse token(@RequestBody @Valid TokenRequest tokenRequest){
         return authservice.authenticate(tokenRequest);
     }
}
