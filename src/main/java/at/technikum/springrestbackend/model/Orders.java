package at.technikum.springrestbackend.model;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private  User user;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Phone> phones = new ArrayList<>();

    private Timestamp timestamp;

    public Orders(User user){
        this.user = user;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

   public void addPhone(Phone phone){phones.add(phone);}
}
