package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.repository.GeneralAvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GeneralAvailabilityService {

    private final GeneralAvailabilityRepository generalAvailabilityRepository;

    public ResponseEntity<List<GeneralAvailability>> getAllAvailabilities() {
        List<GeneralAvailability> availabilities =  generalAvailabilityRepository.findAll();
        return ResponseEntity.ok(availabilities);
    }

    public ResponseEntity<GeneralAvailability> getById(UUID id) {
        Optional<GeneralAvailability> availability = generalAvailabilityRepository.findById(id);
        if (availability.isPresent()) {
            return ResponseEntity.ok(availability.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<GeneralAvailability> create(GeneralAvailability availability) {
        availability.setId(UUID.randomUUID());
        GeneralAvailability savedAvailability = generalAvailabilityRepository.save(availability);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAvailability);
    }

    public ResponseEntity<GeneralAvailability> update(UUID id, GeneralAvailability updatedAvailability) {
        if (!generalAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAvailability.setId(id);
        generalAvailabilityRepository.save(updatedAvailability);
        return ResponseEntity.ok(updatedAvailability);
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (!generalAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        generalAvailabilityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
