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
    private String title;
    @Valid
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "fk_author") //foreign key
    private UserModel author;
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "fk_event_post")
    private EventModel event;
    @NotBlank
    private String content;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    @ElementCollection
    private List<String> mediaPlaceHolder;

    public ForumPostModel() {
    }

    public ForumPostModel(
            String id,
            String title,
            UserModel author,
            String content,
            List<String> mediaPlaceHolder
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllEntity(String id, String title, UserModel author, String content, List<String> mediaPlaceHolder) {
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

    public UserModel getAuthor() {
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

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMediaPlaceHolder(List<String> mediaPlaceHolder) {
        this.mediaPlaceHolder = mediaPlaceHolder;
    }
}
