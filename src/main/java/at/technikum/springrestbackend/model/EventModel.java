package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name="Events")
public class EventModel {

    @Positive
    @Id
    private String eventID;
    @ManyToOne
    @JoinColumn(name = "fk_creator") //foreign key
    private UserModel creator;
    @ManyToMany
    @JoinTable(
            name = "Users",
            joinColumns = @JoinColumn(name = "fk_event"),
            inverseJoinColumns = @JoinColumn(name = "fk_user")
    )
    private List<UserModel> userIDs = new ArrayList<>();


    @OneToMany(mappedBy = "event")
    private List<MediaModel> galleryPictures = new ArrayList<>();

    @OneToMany(mappedBy = "event")
    private List<ForumPostModel> eventPosts = new ArrayList<>();

    private String eventName;
    private String eventPicture;
    private String eventAdress;
    private ZonedDateTime eventDate; // or LocalDateTime without TimeZone
    private String eventShortDescription;
    private String eventLongDescription;

    //Constructor
    public EventModel(){}

    public EventModel(String eventId, UserModel creator, String eventName, String eventPicture, String eventAdress,
                      ZonedDateTime eventDate, String eventShortDescription, String eventLongDescription) {
        this.eventID = eventId;
        this.creator = creator;
        this.eventName = eventName;
        this.eventPicture = eventPicture;
        this.eventAdress = eventAdress;
        this.eventDate = eventDate;
        this.eventShortDescription = eventShortDescription;
        this.eventLongDescription = eventLongDescription;
    }

    public void setAllEventEntity(String eventId, UserModel user, String eventName, String eventPicture, String eventAdress,
                                  ZonedDateTime eventDate, String eventShortDescription, String eventLongDescription) {
        setEventID(eventId);
        setCreator(user);
        setEventName(eventName);
        setEventPicture(eventPicture);
        setEventAdress(eventAdress);
        setEventDate(eventDate);
        setEventShortDescription(eventShortDescription);
        setEventLongDescription(eventLongDescription);
    }


    //Getter and Setter
    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public UserModel getCreator() {
        return creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getEventAdress() {
        return eventAdress;
    }

    public void setEventAdress(String eventAdress) {
        this.eventAdress = eventAdress;
    }

    public ZonedDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(ZonedDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventShortDescription() {
        return eventShortDescription;
    }

    public void setEventShortDescription(String eventShortDescription) {
        this.eventShortDescription = eventShortDescription;
    }

    public String getEventLongDescription() {
        return eventLongDescription;
    }

    public void setEventLongDescription(String eventLongDescription) {
        this.eventLongDescription = eventLongDescription;
    }

    public List<UserModel> getAttendingUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<UserModel> userIDs) {
        this.userIDs = userIDs;
    }

    public List<ForumPostModel> getEventPosts() {
        return eventPosts;
    }

    public void setEventPosts(List<ForumPostModel> eventPosts) {
        this.eventPosts = eventPosts;
    }

    public List<MediaModel> getGalleryPictures() {
        return galleryPictures;
    }

    public void setGalleryPictures(List<MediaModel> galleryPictures) {
        this.galleryPictures = galleryPictures;
    }
}
