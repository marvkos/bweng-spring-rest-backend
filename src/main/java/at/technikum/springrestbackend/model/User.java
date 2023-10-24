package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Min(0)
    private int role;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;

    @Enumerated(EnumType.STRING) // Stellt sicher, dass die Datenbank den Enum-Wert als Zeichenkette speichert
    private Salutation salutation;
    @Email
    private String email;

    private String country;

    private String profilePicture;

    private boolean status;

    public User (String username, String password, int role, String firstname, String lastname, Salutation salutation, String email, String country, String profilePicture, boolean status){
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salutation = salutation;
        this.email = email;
        this.country = country;
        this.profilePicture = profilePicture;
        this.status = status;
    }

}
