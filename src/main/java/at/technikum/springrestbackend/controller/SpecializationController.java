package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Specialization;
import at.technikum.springrestbackend.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    @Autowired
    private SpecializationRepository specializationRepository;

    // Get all specializations
    @GetMapping
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    // Get a specific specialization by ID
    @GetMapping("/{id}")
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable UUID id) {
        Optional<Specialization> specialization = specializationRepository.findById(id);
        if (specialization.isPresent()) {
            return new ResponseEntity<>(specialization.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new specialization
    @PostMapping
    public Specialization createSpecialization(@RequestBody Specialization specialization) {
        specialization.setId(UUID.randomUUID());  // Set a new UUID for the entity
        return specializationRepository.save(specialization);
    }

    // Update a specialization
    @PutMapping("/{id}")
    public ResponseEntity<Specialization> updateSpecialization(@PathVariable UUID id, @RequestBody Specialization specializationDetails) {
        if (!specializationRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        specializationDetails.setId(id);  // Ensure the ID remains the same
        Specialization updatedSpecialization = specializationRepository.save(specializationDetails);
        return new ResponseEntity<>(updatedSpecialization, HttpStatus.OK);
    }

    // Delete a specialization
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSpecialization(@PathVariable UUID id) {
        if (!specializationRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        specializationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
