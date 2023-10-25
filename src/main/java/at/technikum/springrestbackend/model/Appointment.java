package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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

    @FutureOrPresent
    private LocalDateTime startTime;

    @Future
    private LocalDateTime endTime;

    @NotNull
    @ManyToOne
    private Lawyer byLawyer;

    @NotNull
    @ManyToOne
    private User forUser;
}