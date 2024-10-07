package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.EventModel;
import at.technikum.springrestbackend.model.MediaModel;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class UserDTO {
    private String userID;
    private List<EventModel> attendingEvents;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private MediaModel profilePicture;
    private String profileDescription;

    public UserDTO() {
    }

    public UserDTO(String userID, String username, String email) {
        this.userID = userID;
        this.username = username;
        this.email = email;
    }

    public UserDTO(String userID, List<EventModel> attendingEvents, String username, String password, String email, MediaModel profilePicture, String profileDescription) {
        this.userID = userID;
        this.attendingEvents = attendingEvents;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
        this.profileDescription = profileDescription;
    }

    public UserDTO(String userID, List<EventModel> attendingEvents, String username, String email, MediaModel profilePicture, String profileDescription) {
        this.userID = userID;
        this.attendingEvents = attendingEvents;
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
        this.profileDescription = profileDescription;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<EventModel> getAttendingEvents() {
        return attendingEvents;
    }

    public void setAttendingEvents(List<EventModel> attendingEvents) {
        this.attendingEvents = attendingEvents;
    }

    public MediaModel getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(MediaModel profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}
