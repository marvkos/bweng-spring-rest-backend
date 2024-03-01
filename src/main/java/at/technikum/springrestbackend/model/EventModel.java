package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.ZonedDateTime;

@Entity
@Table (name="events")
public class EventModel {

    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String eventId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;
    private String eventName;
    private String eventPicture;
    private String eventAdress;
    private ZonedDateTime eventDate; // or LocalDateTime without TimeZone
    private String eventShortDescription;
    private String eventLongDescription;

    //Constructor
    public EventModel(){}

    public EventModel(String eventId, UserModel user, String eventName, String eventPicture, String eventAdress, ZonedDateTime eventDate, String eventShortDescription, String eventLongDescription) {
        this.eventId = eventId;
        this.user = user;
        this.eventName = eventName;
        this.eventPicture = eventPicture;
        this.eventAdress = eventAdress;
        this.eventDate = eventDate;
        this.eventShortDescription = eventShortDescription;
        this.eventLongDescription = eventLongDescription;
    }

    public void setAllEventEntity(String eventId, UserModel user, String eventName, String eventPicture, String eventAdress,
                                  ZonedDateTime eventDate, String eventShortDescription, String eventLongDescription) {
        setEventId(eventId);
        setUser(user);
        setEventName(eventName);
        setEventPicture(eventPicture);
        setEventAdress(eventAdress);
        setEventDate(eventDate);
        setEventShortDescription(eventShortDescription);
        setEventLongDescription(eventLongDescription);
    }


    //Getter and Setter
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
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
}
