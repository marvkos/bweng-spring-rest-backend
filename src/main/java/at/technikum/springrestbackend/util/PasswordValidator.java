package at.technikum.springrestbackend.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    public static void main(String[] args) {
        String password = "StrongP@ss1";

        if (isValidPassword(password)) {
            System.out.println("Password is valid");
        } else {
            System.out.println("Password is not valid");
        }
    }
}

