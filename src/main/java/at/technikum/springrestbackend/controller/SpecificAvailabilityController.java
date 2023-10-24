package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.SpecificAvailability;
import at.technikum.springrestbackend.repository.SpecificAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/availabilities")
public class SpecificAvailabilityController {

    @Autowired
    private SpecificAvailabilityRepository specificAvailabilityRepository;

    @GetMapping
    public ResponseEntity<List<SpecificAvailability>> getAllAvailabilities() {
        return ResponseEntity.ok((List<SpecificAvailability>) specificAvailabilityRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecificAvailability> getAvailabilityById(@PathVariable UUID id) {
        Optional<SpecificAvailability> availability = specificAvailabilityRepository.findById(id);
        return availability.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SpecificAvailability> createAvailability(@RequestBody SpecificAvailability specificAvailability) {
        specificAvailability.setId(UUID.randomUUID());
        SpecificAvailability savedAvailability = specificAvailabilityRepository.save(specificAvailability);
        return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecificAvailability> updateAvailability(@PathVariable UUID id, @RequestBody SpecificAvailability updatedAvailability) {
        if (!specificAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAvailability.setId(id);
        SpecificAvailability savedAvailability = specificAvailabilityRepository.save(updatedAvailability);
        return ResponseEntity.ok(savedAvailability);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable UUID id) {
        if (!specificAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        specificAvailabilityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
