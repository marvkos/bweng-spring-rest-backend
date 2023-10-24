package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.repository.GeneralAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/general-availabilities")
public class GeneralAvailabilityController {

    @Autowired
    private GeneralAvailabilityRepository repository;

    @GetMapping
    public List<GeneralAvailability> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralAvailability> getById(@PathVariable UUID id) {
        Optional<GeneralAvailability> availability = repository.findById(id);
        if (availability.isPresent()) {
            return ResponseEntity.ok(availability.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GeneralAvailability> create(@RequestBody GeneralAvailability availability) {
        availability.setId(UUID.randomUUID());  // Generate UUID if you wish
        GeneralAvailability savedAvailability = repository.save(availability);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAvailability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralAvailability> update(@PathVariable UUID id, @RequestBody GeneralAvailability updatedAvailability) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAvailability.setId(id);  // Ensure the ID is set
        repository.save(updatedAvailability);
        return ResponseEntity.ok(updatedAvailability);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
