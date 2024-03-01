package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.ForumPostDTO;
import at.technikum.springrestbackend.model.ForumPostModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ForumPostMapper {
    public ForumPostDTO toDTO(ForumPostModel forumPostModel) {
        ForumPostDTO newForumPostDTO = new ForumPostDTO();
        newForumPostDTO.setAllDTO(
                forumPostModel.getId(),
                forumPostModel.getTitle(),
                forumPostModel.getAuthor(),
                forumPostModel.getEvent(),
                forumPostModel.getContent(),
                forumPostModel.getMediaPlaceHolder()
        );
        return newForumPostDTO;
    }

    public ForumPostModel toEntity(ForumPostDTO forumPostDTO) {

        if (forumPostDTO.getId() == null) {
            return new ForumPostModel(
                    UUID.randomUUID().toString(),
                    forumPostDTO.getTitle(),
                    forumPostDTO.getAuthor(),
                    forumPostDTO.getEventID(),
                    forumPostDTO.getContent(),
                    forumPostDTO.getMediaPlaceHolder());
        }

        return new ForumPostModel(
                forumPostDTO.getId(),
                forumPostDTO.getTitle(),
                forumPostDTO.getAuthor(),
                forumPostDTO.getEventID(),
                forumPostDTO.getContent(),
                forumPostDTO.getMediaPlaceHolder());
    }
}
