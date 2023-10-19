package at.technikum.springrestbackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Phone {

    /*

    Need to rework. Fields to do: id, name, description, display-size, memory, battery, price, brand

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;

    private int price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Brand brand;

    public Phone(String title, Brand brand, int price) {
        this.title = title;
        this.brand = brand;
        this.price = price;
    }*/
}
