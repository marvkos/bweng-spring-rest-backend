package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "ForumThreads")
public class ForumThreadModel {
    @Id
    private String id;
    private String title;
    @Valid
    @NotBlank
    private String author;
    @Valid
    @NotBlank
    @ManyToOne
    @JoinColumn(name = "Event_id")
    private Event event;
    @OneToMany
    @JoinColumn(name = "ForumPosts_id")
    private List<ForumPostModel> forumPosts;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    @ElementCollection
    private List<String> mediaPlaceHolder;

    //TODO: possible timestamp field aka metadata

    public ForumThreadModel() {
    }

    public ForumThreadModel(String id, String title, String author, Event event, List<ForumPostModel> forumPosts, List<String> mediaPlaceHolder) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.event = event;
        this.forumPosts = forumPosts;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public void setAllEntity(String id, String title, String author, Event event, List<ForumPostModel> forumPosts, List<String> mediaPlaceHolder) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.event = event;
        this.forumPosts = forumPosts;
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<ForumPostModel> getForumPosts() {
        return forumPosts;
    }

    public void setForumPosts(List<ForumPostModel> forumPosts) {
        this.forumPosts = forumPosts;
    }

    public List<String> getMediaPlaceHolder() {
        return mediaPlaceHolder;
    }

    public void setMediaPlaceHolder(List<String> mediaPlaceHolder) {
        this.mediaPlaceHolder = mediaPlaceHolder;
    }
}
