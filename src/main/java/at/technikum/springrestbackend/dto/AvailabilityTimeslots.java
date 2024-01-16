package at.technikum.springrestbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AvailabilityTimeslots {
    private String day;
    private List<String> timeslots;
}
