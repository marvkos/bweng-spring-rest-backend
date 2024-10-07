package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Media")
public class MediaModel {

    @Id
    private String mediaID;
    private String mediaURL;
    @ManyToOne
    @JoinColumn(name = "fk_event") //foreign key
    private EventModel event;
    @OneToOne(mappedBy = "profilePicture")
    private UserModel user;

    public MediaModel() {
    }

    public MediaModel(String mediaID, String mediaURL, EventModel event) {
        this.mediaID = mediaID;
        this.mediaURL = mediaURL;
        this.event = event;
    }

    public MediaModel(String mediaID, String mediaURL, UserModel user) {
        this.mediaID = mediaID;
        this.mediaURL = mediaURL;
        this.user = user;
    }
}
