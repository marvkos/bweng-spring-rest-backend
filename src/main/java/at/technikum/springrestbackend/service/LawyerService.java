package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.LawyerAvailability;
import at.technikum.springrestbackend.dto.LawyerSearchResult;
import at.technikum.springrestbackend.dto.PagedResults;
import at.technikum.springrestbackend.model.Lawyer;
import at.technikum.springrestbackend.repository.LawyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@AllArgsConstructor
public class LawyerService {

    private final LawyerRepository lawyerRepository;

    public ResponseEntity<Lawyer> createLawyer(Lawyer lawyer) {
        Lawyer savedLawyer = lawyerRepository.save(lawyer);
        return new ResponseEntity<>(savedLawyer, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Lawyer>> getAllLawyers() {
        List<Lawyer> lawyers = lawyerRepository.findAll();
        return ResponseEntity.ok(lawyers);
    }

    public ResponseEntity<Lawyer> getLawyerById(UUID id) {
        Optional<Lawyer> lawyerOptional = lawyerRepository.findById(id);
        if (lawyerOptional.isPresent()) {
            return new ResponseEntity<>(lawyerOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Lawyer> updateLawyer(UUID id, Lawyer lawyer) {
        if (lawyerRepository.existsById(id)) {
            lawyer.setId(id);
            return new ResponseEntity<>(lawyerRepository.save(lawyer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteLawyer(UUID id) {
        if (lawyerRepository.existsById(id)) {
            lawyerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*public ResponseEntity<LawyerAvailability> getLawyerAvailability(
            UUID id,
            LocalDate date,
            int amountOfDays
    ) {
        Optional<Lawyer> lawyerOptional = lawyerRepository.findById(id);
        if (lawyerOptional.isPresent()) {
            Lawyer lawyer = lawyerOptional.get();
            Dictionary<LocalDate, List<LocalDate>> lawyerAvailability = new Hashtable<>();
            for (int i = 0; i < amountOfDays; i++) {
                LocalDate currentDate = date.plusDays(i);
                List<LocalDate> availableTimeslots = new ArrayList<>();
                for (LocalDate specificAvailabilityDate : lawyer.getSpecificAvailabilities().keySet()) {
                    if (specificAvailabilityDate.equals(currentDate)) {
                        availableTimeslots.add(specificAvailabilityDate);
                    }
                }
                lawyerAvailability.put(currentDate, availableTimeslots);
            }
            return new ResponseEntity<>(lawyerAvailability, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    private List<LocalTime> getAvailableTimeslotsForDate(UUID id, LocalDate date) {
        List<LocalTime> availableTimeslots = new ArrayList<>();

        return availableTimeslots;
    }

    public ResponseEntity<PagedResults<LawyerSearchResult>> getLawyersProfilesBySearchTerm(
            String searchTerm,
            int page,
            int size
    ) {
        Page<Lawyer> lawyers = lawyerRepository.findAllByFirstNameContainingOrLastNameContainingOrAddressContainingOrCityContainingOrPostalCodeContaining(
                searchTerm,
                searchTerm,
                searchTerm,
                searchTerm,
                searchTerm,
                PageRequest.of(page, size)
        );
        PagedResults<LawyerSearchResult> lawyerSearchResults = new PagedResults<>(
                lawyers.stream().map(lawyer -> new LawyerSearchResult(
                        lawyer.getFirstName(),
                        lawyer.getLastName(),
                        lawyer.getSpecialization(),
                        lawyer.getHourlyRate(),
                        lawyer.getAddress(),
                        lawyer.getPostalCode(),
                        lawyer.getCity(),
                        new LawyerAvailability(
                                lawyer.getId(),
                                "LocalDate.now()",
                                "LocalDate.now().plusDays(7)",
                                new Hashtable<>()
                        )
                )).toList(),
                page,
                lawyers.getSize(),
                lawyers.getTotalPages()
        );
        return ResponseEntity.ok(lawyerSearchResults);
    }
}
