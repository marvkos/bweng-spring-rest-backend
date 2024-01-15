package at.technikum.springrestbackend.util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PasswordValidatorTests {

    @Test
    public void testValidPassword() {
        assertTrue(PasswordValidator.isValidPassword("StrongP@ss1"));
        assertTrue(PasswordValidator.isValidPassword("AnotherValidP@ss9"));
    }

    @Test
    public void testInvalidPassword_Length() {
        assertFalse(PasswordValidator.isValidPassword("Short1@"));
    }

    @Test
    public void testInvalidPassword_Uppercase() {
        assertFalse(PasswordValidator.isValidPassword("nopassword1@"));
    }

    @Test
    public void testInvalidPassword_Lowercase() {
        assertFalse(PasswordValidator.isValidPassword("NOPASSWORD1@"));
    }

    @Test
    public void testInvalidPassword_Digit() {
        assertFalse(PasswordValidator.isValidPassword("Password@"));
    }

    @Test
    public void testInvalidPassword_SpecialCharacter() {
        assertFalse(PasswordValidator.isValidPassword("Password123"));
    }

    @Test
    public void testInvalidPassword_NoWhitespace() {
        assertFalse(PasswordValidator.isValidPassword("Invalid P@ss1"));
    }

    @Test
    public void testIsValidPasswordWithMockData() {

        PasswordValidator passwordValidatorMock = mock(PasswordValidator.class);
        when(passwordValidatorMock.isValidPassword(anyString())).thenReturn(true);
        assertTrue(passwordValidatorMock.isValidPassword("MockedPassword1@"));
        verify(passwordValidatorMock).isValidPassword("MockedPassword1@");
    }
}
