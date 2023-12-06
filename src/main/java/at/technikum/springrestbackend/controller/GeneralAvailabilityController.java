package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.security.user.UserPrincipal;
import at.technikum.springrestbackend.service.GeneralAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/lawyers/general-availabilities")
public class GeneralAvailabilityController {

    private final GeneralAvailabilityService generalAvailabilityService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<GeneralAvailability>> getAllAvailabilities() {
        return generalAvailabilityService.getAllAvailabilities();
    }

    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.GeneralAvailability', 'read') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<GeneralAvailability> getById(@PathVariable UUID id) {
        return generalAvailabilityService.getById(id);
    }


    @PostMapping
    public ResponseEntity<GeneralAvailability> create(@RequestBody GeneralAvailability availability) {
        // Get the authenticated user (lawyer) from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UUID lawyerId = ((UserPrincipal) authentication.getPrincipal()).getId();

        return generalAvailabilityService.create(availability, lawyerId);
    }

    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.GeneralAvailability', 'write') OR hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<GeneralAvailability> update(@PathVariable UUID id, @RequestBody GeneralAvailability updatedAvailability) {
        return generalAvailabilityService.update(id, updatedAvailability);
    }

    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.GeneralAvailability', 'delete') OR hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return generalAvailabilityService.delete(id);
    }
}
