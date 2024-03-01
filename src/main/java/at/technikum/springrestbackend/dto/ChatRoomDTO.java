package at.technikum.springrestbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ChatRoomDTO {
    private String id;
    private List<UserDTO> users;
    private List<ChatMessageDTO> chatMessages;

    public ChatRoomDTO() {
    }

    public ChatRoomDTO(
            String id,
            List<UserDTO> users,
            List<ChatMessageDTO> chatMessages) {
        this.id = id;
        this.users = users;
        this.chatMessages = chatMessages;
    }

    public void setAllDTO(String id,
                          List<UserDTO> users,
                          List<ChatMessageDTO> chatMessages) {
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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public List<ChatMessageDTO> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessageDTO> chatMessages) {
        this.chatMessages = chatMessages;
    }
}
