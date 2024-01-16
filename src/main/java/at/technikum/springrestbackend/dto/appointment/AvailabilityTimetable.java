package at.technikum.springrestbackend.dto.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Hashtable;
import java.util.List;

@AllArgsConstructor
@Getter
public class AvailabilityTimetable {
    private Hashtable<String, List<String>> availabilityTimetable;
}
