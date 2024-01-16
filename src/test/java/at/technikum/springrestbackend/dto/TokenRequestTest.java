package at.technikum.springrestbackend.dto;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenRequestTest {

    @Test
    void testTokenRequestSettersAndGetters() {
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setUsername("testUser");
        tokenRequest.setPassword("testPass");

        assertEquals("testUser", tokenRequest.getUsername());
        assertEquals("testPass", tokenRequest.getPassword());
    }
}

