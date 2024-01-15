package at.technikum.springrestbackend.dto;

import org.hibernate.validator.HibernateValidator;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
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

