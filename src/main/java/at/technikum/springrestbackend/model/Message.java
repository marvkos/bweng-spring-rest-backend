package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private UUID id;

    private String text;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private File image;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public UUID getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public Date getCreated() {
        return created;
    }
}
