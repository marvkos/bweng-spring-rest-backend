package at.technikum.springrestbackend.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenResponseTest {

    @Test
    void testTokenResponse() {
        String testToken = "testToken123";
        TokenResponse tokenResponse = new TokenResponse(testToken);

        assertEquals(testToken, tokenResponse.getToken());

        String newTestToken = "newTestToken123";
        tokenResponse.setToken(newTestToken);
        assertEquals(newTestToken, tokenResponse.getToken());
    }
}
