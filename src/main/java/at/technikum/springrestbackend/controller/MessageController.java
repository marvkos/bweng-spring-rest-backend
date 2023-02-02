package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.MessageDto;
import at.technikum.springrestbackend.exception.NotYetImplementedException;
import at.technikum.springrestbackend.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageDto> readAll() {
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public MessageDto read(
            @PathVariable UUID id
    ) {
        return messageService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto create(
            @RequestBody @Valid MessageDto messageDto
    ) {
        return messageService.save(messageDto);
    }

    @PutMapping("/{id}")
    public MessageDto update(
            @PathVariable UUID id,
            @RequestBody @Valid MessageDto messageDto
    ) {
        return messageService.update(id, messageDto);
    }

    @DeleteMapping("/{id}")
    public MessageDto delete(
            @PathVariable UUID id
    ) {
        throw new NotYetImplementedException();
    }
}
