package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Lawyer;
import at.technikum.springrestbackend.service.LawyerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Lawyer>> getAllLawyers() {
        return lawyerService.getAllLawyers();
    }

    // Retrieve a single lawyer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lawyer> getLawyerById(@PathVariable UUID id) {
        return lawyerService.getLawyerById(id);
    }

    // Update a lawyer by ID
    @PutMapping("/{id}")
    public ResponseEntity<Lawyer> updateLawyer(@PathVariable UUID id, @RequestBody Lawyer lawyer) {
        return lawyerService.updateLawyer(id, lawyer);
    }

    // Delete a lawyer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLawyer(@PathVariable UUID id) {
        return lawyerService.deleteLawyer(id);
    }
}
