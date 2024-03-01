package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.ChatMessageModel;
import at.technikum.springrestbackend.model.UserModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ChatRoomDTO {
    private String id;
    private List<UserModel> users;
    private List<ChatMessageModel> chatMessages;

    public ChatRoomDTO() {
    }

    public ChatRoomDTO(
            String id,
            List<UserModel> users,
            List<ChatMessageModel> chatMessages) {
        this.id = id;
        this.users = users;
        this.chatMessages = chatMessages;
    }

    public void setAllDTO(String id,
                          List<UserModel> users,
                          List<ChatMessageModel> chatMessages) {
        this.id = id;
        this.users = users;
        this.chatMessages = chatMessages;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

    public List<ChatMessageModel> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessageModel> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
