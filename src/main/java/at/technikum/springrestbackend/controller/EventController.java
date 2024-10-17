package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.EventDTO;
import at.technikum.springrestbackend.mapper.EventMapper;
import at.technikum.springrestbackend.model.EventModel;
import at.technikum.springrestbackend.repository.EventRepository;
import at.technikum.springrestbackend.services.EventServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/events")
@CrossOrigin
public class EventController {

    private final EventMapper eventMapper;
    private final EventServices eventServices;
    private final EventRepository eventRepository;


    @Autowired
    public EventController(
            EventMapper eventMapper,
            EventServices eventServices,
            EventRepository eventRepository) {

        this.eventMapper = eventMapper;
        this.eventServices = eventServices;
        this.eventRepository = eventRepository;
    }

    //Event Routing Controllers

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EventDTO> readAll() {
        return eventServices.findAll().stream()
                .map(eventMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.FOUND)
    public EventDTO read(@PathVariable String eventId) {
        EventModel event = eventServices.find(eventId);
        return eventMapper.toDTO(event);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EventDTO create(@RequestBody @Valid EventDTO eventDTO){
        EventModel event = eventMapper.toEntity(eventDTO);
        eventServices.save(event);
        return eventMapper.toDTO(event);
    }
    @PutMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public EventDTO update(@PathVariable String eventId, @RequestBody EventDTO updatedEventDTO){

        //for update logic CTRL+LMB on 'update' - method call
        return eventMapper.toDTO(eventServices.update(eventId, updatedEventDTO));
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.FOUND)
    public EventDTO delete(@PathVariable String eventId){
        EventDTO deletedEventDTO =
                new EventDTO(
                        eventId,
                        eventServices.find(eventId).getAttendingUserIDs(),
                        eventServices.find(eventId).getGalleryPictures(),
                        eventServices.find(eventId).getEventPosts(),
                        eventServices.find(eventId).getCreator(),
                        eventServices.find(eventId).getEventName(),
                        eventServices.find(eventId).getEventPicture(),
                        eventServices.find(eventId).getEventAdress(),
                        eventServices.find(eventId).getEventDate(),
                        eventServices.find(eventId).getEventShortDescription(),
                        eventServices.find(eventId).getEventLongDescription()

                );
        //Todo: event delete in service, controller to repository nix gut, delete with right userID
        eventServices.find(eventId);
        eventRepository.deleteById(eventId);
        return deletedEventDTO;
    }

    //TODO: Event Users Routing Controllers


    //TODO: Event Media Routing Controllers


}
