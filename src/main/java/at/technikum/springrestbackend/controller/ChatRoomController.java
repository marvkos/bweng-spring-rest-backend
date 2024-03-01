package at.technikum.springrestbackend.controller;
import at.technikum.springrestbackend.dto.ChatRoomDTO;
import at.technikum.springrestbackend.mapper.ChatRoomMapper;
import at.technikum.springrestbackend.model.ChatRoomModel;
import at.technikum.springrestbackend.repository.ChatRoomRepository;
import at.technikum.springrestbackend.services.ChatRoomServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chatroom")
@CrossOrigin
public class ChatRoomController {
    private final ChatRoomMapper chatMapper;
    private final ChatRoomServices chatServices;
    private final ChatRoomRepository chatRepository;

    public ChatRoomController(ChatRoomMapper chatMapper,
                              ChatRoomServices chatServices,
                              ChatRoomRepository chatRepository) {
        this.chatMapper = chatMapper;
        this.chatServices = chatServices;
        this.chatRepository = chatRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ChatRoomDTO> readAll() {
        return chatServices.findAll().stream()
                .map(chatMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ChatRoomDTO read(@PathVariable String id) {
        ChatRoomModel chatRoom = chatServices.find(id);
        return chatMapper.toDTO(chatRoom);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ChatRoomDTO create(@RequestBody @Valid ChatRoomDTO chatRoomDTO){
        ChatRoomModel chatRoom = chatMapper.toEntity(chatRoomDTO);
        chatServices.save(chatRoom);
        return chatMapper.toDTO(chatRoom);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChatRoomDTO update(@PathVariable String id, @RequestBody ChatRoomDTO updatedChatRoomDTO){

        //for update logic CTRL+LMB on 'update' - method call
        return chatMapper.toDTO(chatServices.update(id, updatedChatRoomDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public void delete(@PathVariable String id){
        chatRepository.deleteById(id);
        System.out.println("Chat Room with id: " + id + " deleted successfully!");
    }
}
