package at.technikum.springrestbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Specialization {
    @Id
    private UUID id;
    private String label;
}
