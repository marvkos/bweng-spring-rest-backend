package at.technikum.springrestbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class ForumPostDTO {
    private String id;
    private String title;
    @Valid
    @NotBlank
    private String author;
    @Valid
    @NotBlank
    private String eventID;
    @NotBlank
    private String content;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    private List<String> mediaPlaceHolder;


    //TODO: possible timestamp field aka metadata

    public ForumPostDTO() {
    }

    public ForumPostDTO(
            String id,
            String title,
            String author,
            String eventID,
            String content,
            List<String> mediaPlaceHolder
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.eventID = eventID;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllDTO(String id, String title, String author, String eventID, String content, List<String> mediaPlaceHolder) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.eventID = eventID;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEventID() {
        return eventID;
    }

    public String getContent() {
        return content;
    }

    public List<String> getMediaPlaceHolder() {
        return mediaPlaceHolder;
    }
}
