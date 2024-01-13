package at.technikum.springrestbackend.util;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {

    @Test
    public void testValidPassword() {
        // Arrange
        String validPassword = "StrongP@ss1";

        // Act
        boolean result = PasswordValidator.isValidPassword(validPassword);

        // Assert
        assertTrue(result, "Expected valid password");
    }

    @Test
    public void testInvalidPassword_TooShort() {
        // Arrange
        String invalidPassword = "Short1";

        // Act
        boolean result = PasswordValidator.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result, "Expected invalid password due to length");
    }

    @Test
    public void testInvalidPassword_NoUppercase() {
        // Arrange
        String invalidPassword = "weakp@ss1";

        // Act
        boolean result = PasswordValidator.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result, "Expected invalid password due to no uppercase letter");
    }

    @Test
    public void testInvalidPassword_NoLowercase() {
        // Arrange
        String invalidPassword = "WEAKP@SS1";

        // Act
        boolean result = PasswordValidator.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result, "Expected invalid password due to no lowercase letter");
    }

    @Test
    public void testInvalidPassword_NoDigit() {
        // Arrange
        String invalidPassword = "WeakPass@";

        // Act
        boolean result = PasswordValidator.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result, "Expected invalid password due to no digit");
    }

    @Test
    public void testInvalidPassword_NoSpecialCharacter() {
        // Arrange
        String invalidPassword = "WeakPass1";

        // Act
        boolean result = PasswordValidator.isValidPassword(invalidPassword);

        // Assert
        assertFalse(result, "Expected invalid password due to no special character");
    }
}
