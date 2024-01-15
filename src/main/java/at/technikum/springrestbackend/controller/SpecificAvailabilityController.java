package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.SpecificAvailability;
import at.technikum.springrestbackend.service.SpecificAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/lawyers/availabilities")
public class SpecificAvailabilityController {

    private final SpecificAvailabilityService specificAvailabilityService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<SpecificAvailability>> getAllAvailabilities() {
        return specificAvailabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.SpecificAvailability', 'read')")
    public ResponseEntity<SpecificAvailability> getAvailabilityById(@PathVariable UUID id) {
        return specificAvailabilityService.getAvailabilityById(id);
    }

    @PostMapping
    public ResponseEntity<SpecificAvailability> createAvailability(@RequestBody SpecificAvailability specificAvailability) {
        return specificAvailabilityService.createAvailability(specificAvailability);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.SpecificAvailability', 'write')")
    public ResponseEntity<SpecificAvailability> updateAvailability(@PathVariable UUID id, @RequestBody SpecificAvailability updatedAvailability) {
        return specificAvailabilityService.updateAvailability(id, updatedAvailability);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.SpecificAvailability', 'delete')")
    public ResponseEntity<Void> deleteAvailability(@PathVariable UUID id) {
        return specificAvailabilityService.deleteAvailability(id);
    }
}
