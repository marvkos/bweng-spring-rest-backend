package at.technikum.springrestbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LawyerSearchResult {
    private String firstName;
    private String lastName;
    private String specialization;
    private int hourlyRate;
    private String address;
    private String postalCode;
    private String city;
    private LawyerAvailability availableSlots;
}
