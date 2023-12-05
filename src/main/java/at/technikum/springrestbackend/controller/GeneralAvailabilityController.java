package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.service.GeneralAvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/general-availabilities")
public class GeneralAvailabilityController {

    private final GeneralAvailabilityService generalAvailabilityService;

    @GetMapping
    public ResponseEntity<List<GeneralAvailability>> getAllAvailabilities() {
        return generalAvailabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralAvailability> getById(@PathVariable UUID id) {
        return generalAvailabilityService.getById(id);
    }

    @PostMapping
    public ResponseEntity<GeneralAvailability> create(@RequestBody GeneralAvailability availability) {
        return generalAvailabilityService.create(availability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralAvailability> update(@PathVariable UUID id, @RequestBody GeneralAvailability updatedAvailability) {
        return generalAvailabilityService.update(id, updatedAvailability);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return generalAvailabilityService.delete(id);
    }
}
