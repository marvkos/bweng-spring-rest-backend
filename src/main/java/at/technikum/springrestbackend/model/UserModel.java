package at.technikum.springrestbackend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    private String userID;
    @ManyToMany(mappedBy = "userIDs")
    private List<EventModel> attendingEvents;
    private String username;
    private String password;
    private String email;
    @OneToOne
    @JoinColumn(name = "fk_profilePicture", unique = true) //foreign key
    private MediaModel profilePicture;

    //TODO ForumPost Relatonship missing

    private String profileDescription;
    private boolean isAdmin;


    protected UserModel() {}

    public UserModel(String userId, String username, String password, String email) {
        this.userID = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserModel(String userID, List<EventModel> attendingEvents, String username, String password, String email, MediaModel profilePicture, String profileDescription) {
        this.userID = userID;
        this.attendingEvents = attendingEvents;
        this.username = username;
        this.password = password;
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
