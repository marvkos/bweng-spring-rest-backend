package at.technikum.springrestbackend.util;

import at.technikum.springrestbackend.service.UserService;
import org.springframework.stereotype.Component;
import at.technikum.springrestbackend.model.User;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {
    private final UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    public List<String> validateUserRegistration(User user) {
        List<String> validationErrors = new ArrayList<>();

        if (userService.isUsernameTaken(user.getUsername())) {
            validationErrors.add("Username is already taken");
        }
        if (userService.isEmailTaken(user.getEmail())) {
            validationErrors.add("Email is already taken");
        }
        if (!PasswordValidator.isValidPassword(user.getPassword())) {
            validationErrors.add("Invalid password");
        }
        if(!CountryCodeValidator.isValidCountryCode((user.getCountryCode()))){
            validationErrors.add("Invalid CountryCode");
        }


        return validationErrors;
    }
}
