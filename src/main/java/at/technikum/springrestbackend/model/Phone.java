package at.technikum.springrestbackend.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Phone {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String name;
    private String description;
    @Positive
    private float displaySize;
    @Min(0)
    private int memory;
    @Min(0)
    private int battery;
    @Positive
    private float price;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Phone(String name, String description, float displaySize,
                 int memory, int battery, float price, Brand brand){
        this.name = name;
        this.description = description;
        this.displaySize = displaySize;
        this.memory = memory;
        this.battery = battery;
        this.price = price;
        this.brand = brand;
    }
}
