package at.technikum.springrestbackend.util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CountryCodeValidatorTest {

    @Test
    public void testValidCountryCode() {
        // Arrange
        String validCountryCode = "US";

        // Act
        boolean result = CountryCodeValidator.isValidCountryCode(validCountryCode);

        // Assert
        assertTrue(result, "Expected valid country code");
    }

    @Test
    public void testInvalidCountryCode() {
        // Arrange
        String invalidCountryCode = "XYZ";

        // Act
        boolean result = CountryCodeValidator.isValidCountryCode(invalidCountryCode);

        // Assert
        assertFalse(result, "Expected invalid country code");
    }

    @Test
    public void testNullCountryCode() {
        // Arrange
        String nullCountryCode = null;

        // Act
        boolean result = CountryCodeValidator.isValidCountryCode(nullCountryCode);

        // Assert
        assertFalse(result, "Expected invalid country code for null input");
    }

    @Test
    public void testEmptyCountryCode() {
        // Arrange
        String emptyCountryCode = "";

        // Act
        boolean result = CountryCodeValidator.isValidCountryCode(emptyCountryCode);

        // Assert
        assertFalse(result, "Expected invalid country code for empty input");
    }

    @Test
    public void testWhitespaceCountryCode() {
        // Arrange
        String whitespaceCountryCode = "   ";

        // Act
        boolean result = CountryCodeValidator.isValidCountryCode(whitespaceCountryCode);

        // Assert
        assertFalse(result, "Expected invalid country code for whitespace input");
    }
}
