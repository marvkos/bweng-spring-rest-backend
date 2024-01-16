package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.Appointment;
import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.model.Lawyer;
import at.technikum.springrestbackend.model.SpecificAvailability;
import at.technikum.springrestbackend.repository.AppointmentRepository;
import at.technikum.springrestbackend.repository.GeneralAvailabilityRepository;
import at.technikum.springrestbackend.repository.SpecificAvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppointmentService {
    private final GeneralAvailabilityRepository generalAvailabilityRepository;
    private final SpecificAvailabilityRepository specificAvailabilityRepository;
    private final AppointmentRepository appointmentRepository;

    public ResponseEntity<List<GeneralAvailability>> getAllGeneralAvailabilities() {
        List<GeneralAvailability> availabilities =  generalAvailabilityRepository.findAll();
        return ResponseEntity.ok(availabilities);
    }

    public ResponseEntity<GeneralAvailability> getGeneralAvailabilityById(UUID id) {
        Optional<GeneralAvailability> availability = generalAvailabilityRepository.findById(id);
        if (availability.isPresent()) {
            return ResponseEntity.ok(availability.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<GeneralAvailability> createGeneralAvailability(GeneralAvailability availability) {
        availability.setId(UUID.randomUUID());
        GeneralAvailability savedAvailability = generalAvailabilityRepository.save(availability);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAvailability);
    }

    public ResponseEntity<GeneralAvailability> updateGeneralAvailability(UUID id, GeneralAvailability updatedAvailability) {
        if (!generalAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAvailability.setId(id);
        generalAvailabilityRepository.save(updatedAvailability);
        return ResponseEntity.ok(updatedAvailability);
    }

    public ResponseEntity<Void> deleteGeneralAvailability(UUID id) {
        if (!generalAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        generalAvailabilityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<SpecificAvailability>> getAllSpecificAvailabilities() {
        List<SpecificAvailability> availabilities = specificAvailabilityRepository.findAll();
        return ResponseEntity.ok(availabilities);
    }

    public ResponseEntity<SpecificAvailability> getSpecificAvailabilityById(UUID id) {
        Optional<SpecificAvailability> availability = specificAvailabilityRepository.findById(id);
        return availability.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<SpecificAvailability> createSpecificAvailability(SpecificAvailability specificAvailability) {
        specificAvailability.setId(UUID.randomUUID());
        SpecificAvailability savedAvailability = specificAvailabilityRepository.save(specificAvailability);
        return new ResponseEntity<>(savedAvailability, HttpStatus.CREATED);
    }

    public ResponseEntity<SpecificAvailability> updateSpecificAvailability(UUID id, SpecificAvailability updatedAvailability) {
        if (!specificAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedAvailability.setId(id);
        SpecificAvailability savedAvailability = specificAvailabilityRepository.save(updatedAvailability);
        return ResponseEntity.ok(savedAvailability);
    }

    public ResponseEntity<Void> deleteSpecificAvailability(UUID id) {
        if (!specificAvailabilityRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        specificAvailabilityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private List<String> getTimeslotsByDate(Lawyer lawyer, LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findAllByLawyerAndDate(lawyer, date);
        List<SpecificAvailability> unavailabilities = specificAvailabilityRepository.findAllByDateAndLawyer(date, lawyer);
        List<GeneralAvailability> generalAvailabilities = generalAvailabilityRepository.findAllByDayAndLawyer(
                date.getDayOfWeek(),
                lawyer
        );

        ArrayList<String> timeslots = new ArrayList<>();
        for (GeneralAvailability availability : generalAvailabilities) {
            LocalTime startTime = availability.getStartTime();
            LocalTime endTime = availability.getEndTime();
            LocalTime currentTime = startTime;

            while (currentTime.isBefore(endTime)) {
                LocalTime finalCurrentTime = currentTime;

                boolean lawyerIsAvailable = unavailabilities.stream().noneMatch(
                        unavailability -> finalCurrentTime.isAfter(unavailability.getStartTime())
                                && finalCurrentTime.isBefore(unavailability.getEndTime())
                );

                boolean lawyerHasNoAppointment = appointments.stream().noneMatch(
                        appointment -> finalCurrentTime.isAfter(appointment.getStartTime())
                                && finalCurrentTime.isBefore(appointment.getEndTime())
                );

                if (lawyerIsAvailable && lawyerHasNoAppointment)
                    timeslots.add(currentTime.toString());

                currentTime = currentTime.plusMinutes(availability.getDuration());
            }
        }

        return timeslots;
    }

    public Hashtable<String, List<String>> getAvailabilityTimeslotsForDates(Lawyer lawyer, LocalDate from, int amountOfDays) {
        Hashtable<String, List<String>> availabilityTimeslots = new Hashtable<>();
        for (int i = 0; i < amountOfDays; i++) {
            LocalDate currentDate = from.plusDays(i);
            List<String> availableTimeslots = getTimeslotsByDate(lawyer, currentDate);
            availabilityTimeslots.put(currentDate.toString(), availableTimeslots);
        }
        return availabilityTimeslots;
    }
}
