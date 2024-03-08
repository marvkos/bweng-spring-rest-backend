package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
@Entity
@Table(name = "ChatMessages")
public class ChatMessageModel {
    @Id
    private String id;
    @Valid
    @NotBlank
    private String author;
    @NotBlank
    private String content;
    @Valid
    @NotBlank
    @ManyToOne
    //@JoinColumn(name = "chat_room_id")
    private ChatRoomModel chatRoomModel;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    @ElementCollection
    private List<String> mediaPlaceHolder;

    public ChatMessageModel() {
    }

    public ChatMessageModel(
            String id,
            String author,
            String content,
            ChatRoomModel chatRoomModel,
            List<String> mediaPlaceHolder) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.chatRoomModel = chatRoomModel;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllEntity(String id, String author, String content, ChatRoomModel chatRoomModel, List<String> mediaPlaceHolder) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.chatRoomModel = chatRoomModel;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ChatRoomModel getChatRoomModel() {
        return chatRoomModel;
    }

    public void setChatRoomModel(ChatRoomModel chatRoomModel) {
        this.chatRoomModel = chatRoomModel;
    }

    public List<String> getMediaPlaceHolder() {
        return mediaPlaceHolder;
    }

    public void setMediaPlaceHolder(List<String> mediaPlaceHolder) {
        this.mediaPlaceHolder = mediaPlaceHolder;
    }
}
