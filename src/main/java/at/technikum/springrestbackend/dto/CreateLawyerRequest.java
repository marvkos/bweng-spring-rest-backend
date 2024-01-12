package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.Specialization;
import at.technikum.springrestbackend.model.Lawyer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateLawyerRequest {
    private String firstName;
    private String lastName;
    private Specialization specialization;
    private int hourlyRate;
    private String address;
    private String postalCode;
    private String city;

    public Lawyer toLawyer() {
        Lawyer lawyer = new Lawyer();
        lawyer.setFirstName(firstName);
        lawyer.setLastName(lastName);
        lawyer.setSpecialization(specialization.toString());
        lawyer.setHourlyRate(hourlyRate);
        lawyer.setAddress(address);
        lawyer.setPostalCode(postalCode);
        lawyer.setCity(city);
        return lawyer;
    }
}
