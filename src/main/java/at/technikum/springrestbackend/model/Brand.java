package at.technikum.springrestbackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Brand {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Name of the brand is required")
    private String name;
    @NotBlank(message = "Description of the brand is required")
    private String description;

    private String picturePath;

    public Brand(String name, String description, String picturePath) {
        this.name = name;
        this.description = description;
        this.picturePath = picturePath;
    }

}


