package at.technikum.springrestbackend.security.permission;

import at.technikum.springrestbackend.model.Lawyer;
import at.technikum.springrestbackend.security.user.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorPermission implements AccessPermission {
    @Override
    public boolean supports(Authentication authentication, String className) {
        return className.equals(Lawyer.class.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, UUID resourceId) {
        return ((UserPrincipal) authentication.getPrincipal()).getId().equals(resourceId);
    }
}
