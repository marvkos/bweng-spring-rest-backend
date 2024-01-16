package at.technikum.springrestbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Dictionary;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class LawyerAvailability {
    private String from;
    private String to;
    private Dictionary<String, List<String>> timeslotsByDate;
}
