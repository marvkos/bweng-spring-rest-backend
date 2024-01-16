package at.technikum.springrestbackend.Controller;

import at.technikum.springrestbackend.controller.AuthController;
import at.technikum.springrestbackend.dto.TokenRequest;
import at.technikum.springrestbackend.dto.TokenResponse;
import at.technikum.springrestbackend.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToken_ValidRequest() {
        // Mock the behavior of the AuthService to return a valid TokenResponse
        TokenRequest mockTokenRequest = new TokenRequest("john_doe", "P@ssw0rd");
        TokenResponse mockTokenResponse = new TokenResponse("validToken");
        when(authService.authenticate(mockTokenRequest)).thenReturn(mockTokenResponse);

        // Call the token method on the AuthController with a valid TokenRequest
        TokenResponse result = authController.token(mockTokenRequest);

        // Verify that the result is an OK response with the expected TokenResponse
        assertEquals(mockTokenResponse, result);
    }

    /*@Test
    void testToken_InvalidRequest() {
        // Mock the behavior of the AuthService to throw an exception for an invalid request
        TokenRequest invalidTokenRequest = new TokenRequest("", ""); // Invalid request
        when(authService.authenticate(invalidTokenRequest)).thenThrow(new IllegalArgumentException("Invalid request"));

        // Call the token method on the AuthController with an invalid TokenRequest
     result = authController.token(invalidTokenRequest);

        // Verify that the result is a BAD_REQUEST response
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }*/
}
