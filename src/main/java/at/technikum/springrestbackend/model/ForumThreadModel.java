package at.technikum.springrestbackend.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.List;

@Entity
@Table(name = "forum_threads")
public class ForumThreadModel {
    @Id
    private String id;
    private String title;
    @Valid
    private String author;
    private String content;
//    @OneToMany(mappedBy = "forumThreads")
//    private List<ForumPostModel> forumPosts;

    //media attachments as URL, BLOB or filepath?
    //List for multiple file upload
    @ElementCollection
    private List<String> mediaPlaceHolder;

    public ForumThreadModel() {
    }

    public ForumThreadModel(String id, String title, String author, /*EventModel event, List<ForumPostModel> forumPosts,*/ List<String> mediaPlaceHolder, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
//        this.event = event;
//        this.forumPosts = forumPosts;
        this.mediaPlaceHolder = mediaPlaceHolder;
        this.content = content;
    }

    public void setAllEntity(String id, String title, String author, /*EventModel event, List<ForumPostModel> forumPosts, */List<String> mediaPlaceHolder, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
//        this.event = event;
//        this.forumPosts = forumPosts;
        this.mediaPlaceHolder = mediaPlaceHolder;
        this.content = content;
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

//    public EventModel getEvent() {
//        return event;
//    }
//
//    public void setEvent(EventModel event) {
//        this.event = event;
//    }
//
//    public List<ForumPostModel> getForumPosts() {
//        return forumPosts;
//    }
//
//    public void setForumPosts(List<ForumPostModel> forumPosts) {
//        this.forumPosts = forumPosts;
//    }

    public List<String> getMediaPlaceHolder() {
        return mediaPlaceHolder;
    }

    public void setMediaPlaceHolder(List<String> mediaPlaceHolder) {
        this.mediaPlaceHolder = mediaPlaceHolder;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
