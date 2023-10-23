package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Lawyer {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToOne
    private Specialization specialization;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private String appointmentDuration;
    private Currency feePerHour;
    @OneToMany
    private List<PaymentMethod> paymentMethods;
    @OneToMany
    private List<GeneralAvailability> weekAvailabilities;
    @OneToMany
    private List<SpecificAvailability> unavailabilities;
    @OneToMany
    private List<Appointment> appointments;
}
