package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.dto.appointment.AvailabilityTimetable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LawyerAvailability {
    private String from;
    private String to;
    private AvailabilityTimetable timeslotsByDate;
}
