package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.appointment.AvailabilityTimetable;
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

            while (currentTime.isBefore(endTime) || currentTime.equals(endTime)) {
                LocalTime finalCurrentTime = currentTime;

                boolean lawyerIsAvailable = unavailabilities.stream().noneMatch(
                        unavailability -> timeIsBetween(finalCurrentTime, unavailability.getStartTime(), unavailability.getEndTime().minusSeconds(1))
                );

                boolean lawyerHasNoAppointment = appointments.stream().noneMatch(
                        appointment -> timeIsBetween(finalCurrentTime, appointment.getStartTime(), appointment.getEndTime().minusSeconds(1))
                );

                if (lawyerIsAvailable &&
                        lawyerHasNoAppointment &&
                        !currentTime.plusMinutes(availability.getDuration()).isAfter(endTime))
                    timeslots.add(currentTime.toString());

                currentTime = currentTime.plusMinutes(availability.getDuration());
            }
        }
        return timeslots;
    }

    public ResponseEntity<AvailabilityTimetable> getAvailabilityTimeslotsForDates(
            UUID lawyerId,
            LocalDate from,
            int amountOfDays
    ) {
        Optional<Lawyer> optionalLawyer = lawyerRepository.findById(lawyerId);
        if (optionalLawyer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Lawyer lawyer = optionalLawyer.get();

        TreeMap<String, List<String>> availabilityTimeslots = new TreeMap<>();
        for (int i = 0; i < amountOfDays; i++) {
            LocalDate currentDate = from.plusDays(i);
            List<String> availableTimeslots = getTimeslotsByDate(lawyer, currentDate);
            availabilityTimeslots.put(currentDate.toString(), availableTimeslots);
        }
        return ResponseEntity.ok(new AvailabilityTimetable(availabilityTimeslots));
    }

    public ResponseEntity<Appointment> createAppointment(UUID lawyerId, UUID userId, String date, String time) {
        Optional<Lawyer> lawyer = lawyerRepository.findById(lawyerId);
        if (lawyer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<String> availableTimeslots = getTimeslotsByDate(lawyer.get(), LocalDate.parse(date));
        if (availableTimeslots.stream().noneMatch(timeslot -> LocalTime.parse(time).equals(LocalTime.parse(timeslot)))) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<GeneralAvailability> generalAvailabilities = generalAvailabilityRepository.findAllByDayAndLawyer(
                LocalDate.parse(date).getDayOfWeek(),
                lawyer.get()
        );
        GeneralAvailability generalAvailability = generalAvailabilities.stream().filter(
                availability ->
                        timeIsBetween(LocalTime.parse(time), availability.getStartTime(), availability.getEndTime())
        ).findFirst().orElse(null);
        if (generalAvailability == null) {
            return ResponseEntity.badRequest().build();
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setId(UUID.randomUUID());
        newAppointment.setLawyer(lawyer.get());
        newAppointment.setUser(user.get());
        newAppointment.setDate(LocalDate.parse(date));
        newAppointment.setStartTime(LocalTime.parse(time));
        newAppointment.setEndTime(LocalTime.parse(time).plusMinutes(generalAvailability.getDuration()));
        return ResponseEntity.ok(appointmentRepository.save(newAppointment));
    }

    private boolean timeIsBetween(LocalTime time, LocalTime startTime, LocalTime endTime) {
        return (time.isAfter(startTime) || time.equals(startTime)) && (time.isBefore(endTime) || time.equals(endTime));
    }
}
