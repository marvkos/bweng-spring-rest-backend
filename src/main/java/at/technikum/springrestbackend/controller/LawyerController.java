package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.model.Lawyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/lawyers")
public class LawyerController {

    @Autowired
    private LawyerRepository lawyerRepository;

    // Create a new lawyer
    @PostMapping
    public ResponseEntity<Lawyer> createLawyer(@RequestBody Lawyer lawyer) {
        lawyer.setId(UUID.randomUUID());
        Lawyer savedLawyer = lawyerRepository.save(lawyer);
        return new ResponseEntity<>(savedLawyer, HttpStatus.CREATED);
    }

    // Retrieve all lawyers
    @GetMapping
    public List<Lawyer> getAllLawyers() {
        return lawyerRepository.findAll();
    }

    // Retrieve a single lawyer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Lawyer> getLawyerById(@PathVariable UUID id) {
        Optional<Lawyer> lawyerOptional = lawyerRepository.findById(id);
        if (lawyerOptional.isPresent()) {
            return new ResponseEntity<>(lawyerOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a lawyer by ID
    @PutMapping("/{id}")
    public ResponseEntity<Lawyer> updateLawyer(@PathVariable UUID id, @RequestBody Lawyer lawyer) {
        if (lawyerRepository.existsById(id)) {
            lawyer.setId(id);
            return new ResponseEntity<>(lawyerRepository.save(lawyer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a lawyer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLawyer(@PathVariable UUID id) {
        if (lawyerRepository.existsById(id)) {
            lawyerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
