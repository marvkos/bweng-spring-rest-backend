package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.MessageDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.File;
import at.technikum.springrestbackend.model.Message;
import at.technikum.springrestbackend.repository.FileRepository;
import at.technikum.springrestbackend.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final FileRepository fileRepository;

    private final ModelMapper modelMapper;

    public MessageService(MessageRepository messageRepository, FileRepository fileRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.fileRepository = fileRepository;
        this.modelMapper = modelMapper;
    }

    public List<MessageDto> findAll() {
        return messageRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public MessageDto findById(UUID id) {
        Message message = messageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return toDto(message);
    }

    public MessageDto save(MessageDto messageDto) {
        Message message = toEntity(messageDto);
        message = messageRepository.save(message);
        return toDto(message);
    }

    public MessageDto update(UUID id, MessageDto messageDto) {
        Message message = messageRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Message messageUpdate = toEntity(messageDto);

        message.setText(messageUpdate.getText());
        message.setImage(messageUpdate.getImage());

        message = messageRepository.save(message);

        return toDto(message);
    }

    private MessageDto toDto(Message message) {
        MessageDto messageDto = modelMapper.map(message, MessageDto.class);

        if (message.getImage() != null) {
            messageDto.setImage("files/" + message.getImage().getId());
        }

        return messageDto;
    }

    private Message toEntity(MessageDto messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);

        if (messageDto.getImage() != null) {
            String imageId = messageDto.getImage().replace("files/", "");
            File image = fileRepository.findById(UUID.fromString(imageId))
                    .orElseThrow(EntityNotFoundException::new);
            image.setAssigned(true);
            message.setImage(image);
        }

        return message;
    }
}
