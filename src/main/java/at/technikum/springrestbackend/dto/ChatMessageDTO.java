package at.technikum.springrestbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ChatMessageDTO {
    private String id;
    @Valid
    @NotBlank
    private String authorid;
    @NotBlank
    private String content;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    private List<String> mediaPlaceHolder;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(
            String id,
            String author,
            String content,
            List<String> mediaPlaceHolder
    ) {
        this.id = id;
        this.authorid = author;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllDTO(String id, String author, String content, List<String> mediaPlaceHolder) {
        this.id = id;
        this.authorid = author;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMediaPlaceHolder() {
        return mediaPlaceHolder;
    }

    public void setMediaPlaceHolder(List<String> mediaPlaceHolder) {
        this.mediaPlaceHolder = mediaPlaceHolder;
    }
}
