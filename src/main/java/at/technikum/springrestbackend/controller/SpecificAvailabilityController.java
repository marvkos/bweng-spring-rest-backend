package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.SpecificAvailability;
import at.technikum.springrestbackend.service.SpecificAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/availabilities")
public class SpecificAvailabilityController {

    private final SpecificAvailabilityService specificAvailabilityService;

    @GetMapping
    public ResponseEntity<List<SpecificAvailability>> getAllAvailabilities() {
        return specificAvailabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecificAvailability> getAvailabilityById(@PathVariable UUID id) {
        return specificAvailabilityService.getAvailabilityById(id);
    }

    @PostMapping
    public ResponseEntity<SpecificAvailability> createAvailability(@RequestBody SpecificAvailability specificAvailability) {
        return specificAvailabilityService.createAvailability(specificAvailability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecificAvailability> updateAvailability(@PathVariable UUID id, @RequestBody SpecificAvailability updatedAvailability) {
        return specificAvailabilityService.updateAvailability(id, updatedAvailability);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable UUID id) {
        return specificAvailabilityService.deleteAvailability(id);
    }
}
