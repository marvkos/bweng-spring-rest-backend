package at.technikum.springrestbackend.security.permission;

import at.technikum.springrestbackend.security.user.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserPermission implements AccessPermission {

    @Override
    public boolean supports(Authentication authentication, String className) {
        // Generalize support for all classes
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, UUID resourceId) {
        // Check if the authenticated user's ID matches the resource ID
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return principal.getId().equals(resourceId);
    }
}
