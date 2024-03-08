package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.ChatRoomDTO;
import at.technikum.springrestbackend.model.ChatRoomModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChatRoomMapper {

    public ChatRoomDTO toDTO(ChatRoomModel chatRoomModel) {
        ChatRoomDTO newchatRoomDTO = new ChatRoomDTO();
        newchatRoomDTO.setAllDTO(
                chatRoomModel.getId(),
                chatRoomModel.getUsers(),
                chatRoomModel.getChatMessages()
        );
        return newchatRoomDTO;
    }

    public ChatRoomModel toEntity(ChatRoomDTO chatRoomDTO) {

        if (chatRoomDTO.getId() == null) {
            return new ChatRoomModel(
                    UUID.randomUUID().toString(),
                    chatRoomDTO.getChatMessages(),
                    chatRoomDTO.getUsers());
        }

        return new ChatRoomModel(
                chatRoomDTO.getId(),
                chatRoomDTO.getChatMessages(),
                chatRoomDTO.getUsers());
    }
}
