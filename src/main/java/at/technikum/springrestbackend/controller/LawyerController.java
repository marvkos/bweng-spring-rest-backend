package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Lawyer;
import at.technikum.springrestbackend.service.LawyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/lawyers")
public class LawyerController {

    private final LawyerService lawyerService;

    // Create a new lawyer
    @PostMapping
    public ResponseEntity<Lawyer> createLawyer(@RequestBody Lawyer lawyer) {
        return lawyerService.createLawyer(lawyer);
    }

    // Retrieve all lawyers
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Lawyer>> getAllLawyers() {
        return lawyerService.getAllLawyers();
    }

    // Retrieve a single lawyer by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.Lawyer', 'read')")
    public ResponseEntity<Lawyer> getLawyerById(@PathVariable UUID id) {
        return lawyerService.getLawyerById(id);
    }

    // Update a lawyer by ID
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.Lawyer', 'write')")
    public ResponseEntity<Lawyer> updateLawyer(@PathVariable UUID id, @RequestBody Lawyer updatedLawyer) {
        return lawyerService.updateLawyer(id, updatedLawyer);
    }

    // Delete a lawyer by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasPermission(#id, 'at.technikum.springrestbackend.model.Lawyer', 'delete')")
    public ResponseEntity<Void> deleteLawyer(@PathVariable UUID id) {
        return lawyerService.deleteLawyer(id);
    }
}
