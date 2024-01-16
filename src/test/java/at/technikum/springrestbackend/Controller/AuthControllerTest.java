package at.technikum.springrestbackend.Controller;
import at.technikum.springrestbackend.controller.AuthController;
import at.technikum.springrestbackend.dto.TokenRequest;
import at.technikum.springrestbackend.dto.TokenResponse;
import at.technikum.springrestbackend.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;
  
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void shouldReturnTokenWhenValidRequest() throws Exception {
        TokenRequest tokenRequest = new TokenRequest();
        TokenResponse tokenResponse = new TokenResponse("token");
        given(authService.authenticate(any(TokenRequest.class))).willReturn(tokenResponse);

        mockMvc.perform(post("/auth/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"user\", \"password\": \"pass\" }"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"token\"}"));
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

   
}
