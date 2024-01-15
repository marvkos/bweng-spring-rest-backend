package at.technikum.springrestbackend.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
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
    @NotBlank(message = "name required")
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

    private String picture;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    //Does not need NOT Blank because will be set manually
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User createdBy;

    public Phone(String name, String description, float displaySize,
                 int memory, int battery, float price, Brand brand, User user, String picture){
        this.name = name;
        this.description = description;
        this.displaySize = displaySize;
        this.memory = memory;
        this.battery = battery;
        this.price = price;
        this.brand = brand;
        this.createdBy = user;
        this.picture = picture;
    }
}
