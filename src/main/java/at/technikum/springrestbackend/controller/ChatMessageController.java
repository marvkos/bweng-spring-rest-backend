package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.ChatMessageDTO;
import at.technikum.springrestbackend.mapper.ChatMessageMapper;
import at.technikum.springrestbackend.model.ChatMessageModel;
import at.technikum.springrestbackend.repository.ChatMessageRepository;
import at.technikum.springrestbackend.services.ChatMessageServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatmessages")
@CrossOrigin
public class ChatMessageController {
    private final ChatMessageMapper chatMapper;
    private final ChatMessageServices chatServices;
    private final ChatMessageRepository chatRepository;

    public ChatMessageController(ChatMessageMapper chatMapper,
                                 ChatMessageServices chatServices,
                                 ChatMessageRepository chatRepository) {
        this.chatMapper = chatMapper;
        this.chatServices = chatServices;
        this.chatRepository = chatRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ChatMessageDTO> readAll() {
        return chatServices.findAll().stream()
                .map(chatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ChatMessageDTO read(@PathVariable String id) {
        ChatMessageModel chatMessage = chatServices.find(id);
        return chatMapper.toDTO(chatMessage);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ChatMessageDTO create(@RequestBody @Valid ChatMessageDTO chatDTO){
        ChatMessageModel chatMessage = chatMapper.toEntity(chatDTO);
        chatServices.save(chatMessage);
        return chatMapper.toDTO(chatMessage);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChatMessageDTO update(@PathVariable String id, @RequestBody ChatMessageDTO updatedChatMessageDTO){

        //for update logic CTRL+LMB on 'update' - method call
        return chatMapper.toDTO(chatServices.update(id, updatedChatMessageDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void delete(@PathVariable String id){
//        chatServices.find(id);
        chatRepository.deleteById(id);
        System.out.println("Chat Message with id: " + id + " deleted successfully!");

    }
}
