package at.technikum.springrestbackend.jwt;

import at.technikum.springrestbackend.property.JwtProperties;
import at.technikum.springrestbackend.security.jwt.JwtIssuer;
import com.auth0.jwt.JWT;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class JwtIssuerTest {

    @Mock
    private JwtProperties jwtProperties;

    @InjectMocks
    private JwtIssuer jwtIssuer;

    @Test
    void issue_shouldGenerateJwtToken() {
        // Arrange
        UUID userId = UUID.randomUUID();
        String username = "test";
        String role = "ROLE_USER";

        when(jwtProperties.getSecret()).thenReturn("yourSecretKey");

        // Act
        String generatedToken = jwtIssuer.issue(userId, username, role);

        // Assert
        assertNotNull(generatedToken);

        String extractedUserId = JWT.decode(generatedToken).getSubject();
        String extractedUsername = JWT.decode(generatedToken).getClaim("username").asString();
        String extractedRole = JWT.decode(generatedToken).getClaim("role").asString();

        assertEquals(userId.toString(), extractedUserId);
        assertEquals(username, extractedUsername);
        assertEquals(role, extractedRole);
    }
}
