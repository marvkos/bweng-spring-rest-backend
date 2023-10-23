package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Appointment {
    @Id
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    private Lawyer byLawyer;
    @ManyToOne
    private User forUser;
}