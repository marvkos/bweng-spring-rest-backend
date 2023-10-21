package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
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

    private String username;

    private String password;

    private int role;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING) // Stellt sicher, dass die Datenbank den Enum-Wert als Zeichenkette speichert
    private Salutation salutation;

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
