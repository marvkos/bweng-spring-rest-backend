package at.technikum.springrestbackend.services;


import at.technikum.springrestbackend.dto.ChatRoomDTO;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.ChatRoomModel;
import at.technikum.springrestbackend.repository.ChatRoomRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServices {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomServices(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public boolean idExists(String id){
        return chatRoomRepository.existsById(id);
    }
    public ChatRoomModel find(String id) {
        return chatRoomRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("Chat Room not found with id: " + id));
    }

    public List<ChatRoomModel> findAll (){
        return chatRoomRepository.findAll();
    }

    public ChatRoomModel save(ChatRoomModel chatRoomModel){
        return chatRoomRepository.save(chatRoomModel);
    }

    public ChatRoomModel update(String id, ChatRoomDTO chatRoomDTOupdated){

        //catching the case when an entity with the id does not exist
        if (!idExists(id)){
            throw new EntityNotFoundException("Chat Room with provided ID [" + id + "] not found.");
        }

        //get the existing Post from the DB and THEN set new values
        ChatRoomModel existingChatRoom = chatRoomRepository.findById(id).orElseThrow();

        //author, eventID and MAYBE media (?) are redundant
        existingChatRoom.setAllEntity(
                id,
                chatRoomDTOupdated.getChatMessages(),
                chatRoomDTOupdated.getUsers()
        );


        return chatRoomRepository.save(existingChatRoom);
    }
}
