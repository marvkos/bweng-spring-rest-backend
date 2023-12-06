package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Specialization;
import at.technikum.springrestbackend.service.SpecializationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/lawyers/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    // Get all specializations
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }

    // Get a specific specialization by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.Specialization', 'delete') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable UUID id) {
        return specializationService.getSpecializationById(id);
    }

    // Create a new specialization
    @PostMapping
    public Specialization createSpecialization(@RequestBody Specialization specialization) {
        return specializationService.createSpecialization(specialization);
    }

    // Update a specialization
    @PutMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.Specialization', 'delete') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<Specialization> updateSpecialization(@PathVariable UUID id, @RequestBody Specialization specializationDetails) {
        return specializationService.updateSpecialization(id, specializationDetails);
    }

    // Delete a specialization
    @DeleteMapping("/{id}")
    @PreAuthorize("hasPermission(#id, 'at.technikum.springrestbackend.model.Specialization', 'delete') OR hasRole('ROLE_ADMIN')")
    public ResponseEntity<HttpStatus> deleteSpecialization(@PathVariable UUID id) {
        return specializationService.deleteSpecialization(id);
    }
}
