package at.technikum.springrestbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Blog {

    @Id
    private UUID id;

    @NotBlank
    private String title;

    @Column(length = 1000)
    @Size(min = 3, max = 1000)
    private String content;

    @NotBlank
    private String author;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant lastUpdatedOn;

    private String photoBucket;

    private String photoName;

    public Blog(String title, String content, String author){
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
