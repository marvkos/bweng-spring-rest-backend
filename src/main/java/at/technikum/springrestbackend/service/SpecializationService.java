package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.Specialization;
import at.technikum.springrestbackend.repository.SpecializationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SpecializationService {

    private final SpecializationRepository specializationRepository;

    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        List<Specialization> specializations = specializationRepository.findAll();
        return ResponseEntity.ok(specializations);
    }

    public ResponseEntity<Specialization> getSpecializationById(UUID id) {
        Optional<Specialization> specialization = specializationRepository.findById(id);
        if (specialization.isPresent()) {
            return new ResponseEntity<>(specialization.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Specialization createSpecialization(Specialization specialization) {
        specialization.setId(UUID.randomUUID());  // Set a new UUID for the entity
        return specializationRepository.save(specialization);
    }

    public ResponseEntity<Specialization> updateSpecialization(UUID id, Specialization specializationDetails) {
        if (!specializationRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        specializationDetails.setId(id);  // Ensure the ID remains the same
        Specialization updatedSpecialization = specializationRepository.save(specializationDetails);
        return new ResponseEntity<>(updatedSpecialization, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteSpecialization(UUID id) {
        if (!specializationRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        specializationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
