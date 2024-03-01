package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.ChatMessageDTO;
import at.technikum.springrestbackend.model.ChatMessageModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChatMessageMapper {

    public ChatMessageDTO toDTO(ChatMessageModel chatMessageModel) {
        ChatMessageDTO newchatMessageDTO = new ChatMessageDTO();
        newchatMessageDTO.setAllDTO(
                chatMessageModel.getId(),
                chatMessageModel.getAuthor(),
                chatMessageModel.getRecipientid(),
                chatMessageModel.getEventID(),
                chatMessageModel.getContent(),
                chatMessageModel.getMediaPlaceHolder()
        );
        return newchatMessageDTO;
    }

    public ChatMessageModel toEntity(ChatMessageDTO chatMessageDTO) {

        if (chatMessageDTO.getId() == null) {
            return new ChatMessageModel(
                    UUID.randomUUID().toString(),
                    chatMessageDTO.getAuthorid(),
                    chatMessageDTO.getRecipientid(),
                    chatMessageDTO.getEventID(),
                    chatMessageDTO.getContent(),
                    chatMessageDTO.getMediaPlaceHolder());
        }

        return new ChatMessageModel(
                chatMessageDTO.getId(),
                chatMessageDTO.getAuthorid(),
                chatMessageDTO.getRecipientid(),
                chatMessageDTO.getEventID(),
                chatMessageDTO.getContent(),
                chatMessageDTO.getMediaPlaceHolder());
    }
}
