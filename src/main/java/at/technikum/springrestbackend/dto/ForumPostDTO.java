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
            String content,
            List<String> mediaPlaceHolder
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllDTO(String id, String title, String author, String content, List<String> mediaPlaceHolder) {
        this.id = id;
        this.title = title;
        this.author = author;
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

    public String getContent() {
        return content;
    }

    public List<String> getMediaPlaceHolder() {
        return mediaPlaceHolder;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMediaPlaceHolder(List<String> mediaPlaceHolder) {
        this.mediaPlaceHolder = mediaPlaceHolder;
    }
}
