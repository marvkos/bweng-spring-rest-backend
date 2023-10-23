package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@Entity
public class GeneralAvailability {
    @Id
    private UUID id;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    private Lawyer forLawyer;
}
