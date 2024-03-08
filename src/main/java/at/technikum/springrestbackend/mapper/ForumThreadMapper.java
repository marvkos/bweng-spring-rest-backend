package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.ForumThreadDTO;
import at.technikum.springrestbackend.model.EventModel;
import at.technikum.springrestbackend.model.ForumThreadModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ForumThreadMapper {
    public ForumThreadDTO toDTO(ForumThreadModel forumThreadModel) {
        ForumThreadDTO newForumThreadDTO = new ForumThreadDTO();
        newForumThreadDTO.setAllDTO(
                forumThreadModel.getId(),
                forumThreadModel.getTitle(),
                forumThreadModel.getAuthor(),
                forumThreadModel.getEvent(),
                forumThreadModel.getContent(),
                forumThreadModel.getMediaPlaceHolder(),
                forumThreadModel.getForumPosts()
        );
        return newForumThreadDTO;
    }

    public ForumThreadModel toEntity(ForumThreadDTO forumThreadDTO) {
        EventModel event = new EventModel();

        if (forumThreadDTO.getId() == null) {
            return new ForumThreadModel(
                    UUID.randomUUID().toString(),
                    forumThreadDTO.getTitle(),
                    forumThreadDTO.getAuthor(),
                    event,
                    forumThreadDTO.getForumPosts(),
                    forumThreadDTO.getMediaPlaceHolder(),
                    forumThreadDTO.getContent());
        }

        return new ForumThreadModel(
                forumThreadDTO.getId(),
                forumThreadDTO.getTitle(),
                forumThreadDTO.getAuthor(),
                event,
                forumThreadDTO.getForumPosts(),
                forumThreadDTO.getMediaPlaceHolder(),
                forumThreadDTO.getContent());
    }
}
