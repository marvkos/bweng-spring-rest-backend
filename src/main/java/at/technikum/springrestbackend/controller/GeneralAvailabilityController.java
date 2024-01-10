package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.service.GeneralAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.GeneralAvailability', 'read') ")
    @GetMapping("/{id}")
    public ResponseEntity<GeneralAvailability> getById(@PathVariable UUID id) {
        return generalAvailabilityService.getById(id);
    }


    @PostMapping
    public ResponseEntity<GeneralAvailability> create(@RequestBody GeneralAvailability availability) {
        return generalAvailabilityService.create(availability);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.GeneralAvailability', 'write') ")
    @PutMapping("/{id}")
    public ResponseEntity<GeneralAvailability> update(@PathVariable UUID id, @RequestBody GeneralAvailability updatedAvailability) {
        return generalAvailabilityService.update(id, updatedAvailability);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.GeneralAvailability', 'delete') ")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return generalAvailabilityService.delete(id);
    }
}
