package at.technikum.springrestbackend.services;


import at.technikum.springrestbackend.dto.ChatMessageDTO;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.ChatMessageModel;
import at.technikum.springrestbackend.repository.ChatMessageRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServices {
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessageServices(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public boolean idExists(String id){
        return chatMessageRepository.existsById(id);
    }
    public ChatMessageModel find(String id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("Message not found with id: " + id));
    }

    public List<ChatMessageModel> findAll (){
        return chatMessageRepository.findAll();
    }

    public ChatMessageModel save(ChatMessageModel chatMessageModel){
        return chatMessageRepository.save(chatMessageModel);
    }

    public ChatMessageModel update(String id, ChatMessageDTO chatMessageDTOupdated){

        //catching the case when an entity with the id does not exist
        if (!idExists(id)){
            throw new EntityNotFoundException("Chat Message with provided ID [" + id + "] not found.");
        }

        //get the existing Post from the DB and THEN set new values
        ChatMessageModel existingChatMessage = chatMessageRepository.findById(id).orElseThrow();

        //author, eventID and MAYBE media (?) are redundant
        existingChatMessage.setAllEntity(
                id,
                chatMessageDTOupdated.getAuthorid(),
                chatMessageDTOupdated.getContent(),
                chatMessageDTOupdated.getChat,
                chatMessageDTOupdated.getMediaPlaceHolder());

        return chatMessageRepository.save(existingChatMessage);
    }
}
