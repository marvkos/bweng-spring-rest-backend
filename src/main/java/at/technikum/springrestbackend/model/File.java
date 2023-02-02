package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private UUID id;

    private String originalFileName;

    private String contentType;

    private boolean assigned;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public UUID getId() {
        return id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public Date getCreated() {
        return created;
    }
}
