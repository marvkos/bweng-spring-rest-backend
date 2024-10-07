package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.ForumPostModel;
import at.technikum.springrestbackend.model.MediaModel;
import at.technikum.springrestbackend.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.List;


public class EventDTO {

    private String eventID;
    @NotBlank
    private UserModel creator;
    private List<UserModel> attendingUsers;
    private List<MediaModel> galleryPictures;
    private List<ForumPostModel> eventPosts;
    @NotBlank
    private String eventName;
    @NotBlank
    private String eventPicture;
    @NotBlank
    @Valid
    private String eventLocation;
    @NotBlank
    private ZonedDateTime eventDate; // or LocalDateTime without TimeZone
    @NotBlank
    private String eventShortDescription;
    @NotBlank
    private String eventLongDescription;

    //Constructor
    public EventDTO(){
    }

    public EventDTO(String eventId, List<UserModel> userIDs, List<MediaModel> pictures, List<ForumPostModel> eventPosts,
                    UserModel creator, String eventName, String eventPicture, String eventAdress, ZonedDateTime eventDate,
                    String eventShortDescription, String eventLongDescription) {
        this.eventID = eventId;
        this.creator = creator;
        this.eventName = eventName;
        this.eventPicture = eventPicture;
        this.eventLocation = eventAdress;
        this.eventDate = eventDate;
        this.eventShortDescription = eventShortDescription;
        this.eventLongDescription = eventLongDescription;
        this.attendingUsers = userIDs;
        this.galleryPictures = pictures;
        this.eventPosts = eventPosts;
    }

    public void setAllEventDTO(String eventId, UserModel creator, String eventName, String eventPicture, String eventAdress,
                                  ZonedDateTime eventDate, String eventShortDescription, String eventLongDescription,
                                  List<UserModel> userIDs, List<MediaModel> pictures, List<ForumPostModel> eventPosts) {
        setEventID(eventId);
        setCreator(creator);
        setEventName(eventName);
        setEventPicture(eventPicture);
        setEventLocation(eventAdress);
        setEventDate(eventDate);
        setEventShortDescription(eventShortDescription);
        setEventLongDescription(eventLongDescription);
        setEventPosts(eventPosts);
        setGalleryPictures(pictures);
        setAttendingUsers(userIDs);
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

    public void setCreator(UserModel user) {
        this.creator = user;
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

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
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

    public List<UserModel> getAttendingUsers() {
        return attendingUsers;
    }

    public void setAttendingUsers(List<UserModel> attendingUsers) {
        this.attendingUsers = attendingUsers;
    }

    public List<MediaModel> getGalleryPictures() {
        return galleryPictures;
    }

    public void setGalleryPictures(List<MediaModel> galleryPictures) {
        this.galleryPictures = galleryPictures;
    }

    public List<ForumPostModel> getEventPosts() {
        return eventPosts;
    }

    public void setEventPosts(List<ForumPostModel> eventPosts) {
        this.eventPosts = eventPosts;
    }
}
