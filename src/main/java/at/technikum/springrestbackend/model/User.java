package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class User {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany
    private List<Appointment> appointments;
}
