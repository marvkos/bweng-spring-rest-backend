package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "ForumPosts")
public class ForumPostModel {
    @Id
    private String id;
    @Valid
    @NotBlank
    private String author;
    @NotBlank
    private String content;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    @ElementCollection
    private List<String> mediaPlaceHolder;

    //TODO: possible timestamp field aka metadata

    public ForumPostModel() {
    }

    public ForumPostModel(
            String id,
            String author,
            String content,
            List<String> mediaPlaceHolder
    ) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllEntity(String id, String author, String content, List<String> mediaPlaceHolder) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public String getId() {
        return id;
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
