package at.technikum.springrestbackend.util;
import java.util.Locale;

//uses java.util.Locale library to validate country code
public class CountryCodeValidator {

    public static boolean isValidCountryCode(String countryCode) {
        if (countryCode == null || countryCode.trim().isEmpty()) {
            return false;
        }

        //Fetches all ISO Country Codes
        String[] countryCodes = Locale.getISOCountries();
        //Checks if the String is one of the Country Codes
        for (String code : countryCodes) {
            if (code.equals(countryCode.toUpperCase())) {
                return true;
            }
        }

        return false;
    }



}
