package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.model.*;
import at.technikum.springrestbackend.repository.*;
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
    private final LawyerRepository lawyerRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

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

    public ResponseEntity<Appointment> createAppointment(UUID lawyerId, UUID userId, String date, String time) {
        // We verify that the lawyer exists
        Optional<Lawyer> lawyer = lawyerRepository.findById(lawyerId);
        if (lawyer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // We verify that the user exists
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // We verify that the lawyer has a general availability at the given time
        List<GeneralAvailability> generalAvailabilities = generalAvailabilityRepository.findAllByDayAndLawyer(
                LocalDate.parse(date).getDayOfWeek(),
                lawyer.get()
        );
        GeneralAvailability generalAvailability = generalAvailabilities.stream().filter(
                availability -> LocalTime.parse(time).isAfter(availability.getStartTime())
                        && LocalTime.parse(time).isBefore(availability.getEndTime())
        ).findFirst().orElse(null);
        if (generalAvailability == null) {
            return ResponseEntity.badRequest().build();
        }

        // We verify that the appointment is not already taken
        Optional<Appointment> appointment = appointmentRepository.findByLawyerAndDateAndStartTime(
                lawyer.get(),
                LocalDate.parse(date),
                LocalTime.parse(time)
        );
        if (appointment.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        // We verify that the lawyer is not unavailable at the given time
        List<SpecificAvailability> unavailabilities = specificAvailabilityRepository.findAllByDateAndLawyer(
                LocalDate.parse(date),
                lawyer.get()
        );
        if (unavailabilities.stream().anyMatch(
                unavailability -> LocalTime.parse(time).isAfter(unavailability.getStartTime())
                        && LocalTime.parse(time).isBefore(unavailability.getEndTime())
        )) {
            return ResponseEntity.badRequest().build();
        }

        // We create the appointment;
        Appointment savedAppointment = appointmentRepository.save(new Appointment() {{
            setId(UUID.randomUUID());
            setLawyer(lawyer.get());
            setUser(user.get());
            setDate(LocalDate.parse(date));
            setStartTime(LocalTime.parse(time));
            setEndTime(LocalTime.parse(time).plusMinutes(generalAvailability.getDuration()));
        }});

        return ResponseEntity.ok(savedAppointment);
    }
}
