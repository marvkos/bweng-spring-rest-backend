package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.ForumPostModel;
import at.technikum.springrestbackend.model.ForumThreadModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ForumThreadDTO {
    private String id;
    private String title;
    @Valid
    @NotBlank
    private String author;
    @Valid
    @NotBlank
    private EventModel event;
    @NotBlank
    private String content;

    private List<ForumPostModel> forumPosts;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    private List<String> mediaPlaceHolder;


    //TODO: possible timestamp field aka metadata

    public ForumThreadDTO() {
    }

    public ForumThreadDTO(
            String id,
            String title,
            String author,
            EventModel event,
            String content,
            List<String> mediaPlaceHolder,
            List<ForumPostModel> threadModel) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.event = event;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
        this.forumPosts = threadModel;
    }

    public void setAllDTO(
            String id,
            String title,
            String author,
            EventModel event,
            String content,
            List<String> mediaPlaceHolder,
            List<ForumPostModel> threadModel) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.event = event;
        this.content = content;
        this.mediaPlaceHolder = mediaPlaceHolder;
        this.forumPosts = threadModel;
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

    public EventModel getEvent() {
        return event;
    }

    public void setEvent(EventModel event) {
        this.event = event;
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

    public List<ForumPostModel> getForumPosts() {
        return forumPosts;
    }

    public void setForumPosts(List<ForumPostModel> forumPosts) {
        this.forumPosts = forumPosts;
    }
}
