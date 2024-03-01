package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "ChatRoom")
public class ChatRoomModel {

    @Id
    private String id;
    @OneToMany
    @JoinColumn(name = "ChatMessage_id")
    private List<ChatMessageModel> chatMessages;
    @NotBlank
    @ManyToMany
    @JoinColumn(name = "Users_id")
    private List<UserModel> users;


    public ChatRoomModel() {
    }

    public ChatRoomModel(String id, List<ChatMessageModel> chatMessages, List<UserModel> users) {
        this.id = id;
        this.chatMessages = chatMessages;
        this.users = users;
    }

    public void setAllEntity(String id, List<ChatMessageModel> chatMessages, List<UserModel> users) {
        this.id = id;
        this.chatMessages = chatMessages;
        this.users = users;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChatMessageModel> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessageModel> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }
}
