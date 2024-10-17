package at.technikum.springrestbackend.services;

import at.technikum.springrestbackend.dto.LoginRequestDTO;
import at.technikum.springrestbackend.dto.LoginResponseDTO;
import at.technikum.springrestbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid credentials", e);
        }

        // Load user details and generate JWT token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return new LoginResponseDTO(jwt);
    }
}