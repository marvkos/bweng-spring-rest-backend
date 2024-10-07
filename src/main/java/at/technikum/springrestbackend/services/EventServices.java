package at.technikum.springrestbackend.services;

import at.technikum.springrestbackend.dto.EventDTO;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.EventModel;
import at.technikum.springrestbackend.repository.EventRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class EventServices {
    private final EventRepository eventRepository;

    public EventServices(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public boolean idExists(String id){
        return eventRepository.existsById(id);
    }

    public EventModel find(String id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("Event not found with id: " + id));
    }

    public EventModel update(String id, EventDTO eventDTOupdate) {

        //catching the case when an entity with the id does not exist
        if (!idExists(id)) {
            throw new EntityNotFoundException("Event with provided ID [" + id + "] not found.");
        }
        //get the existing Event from the DB and THEN set new values
        EventModel existingEvent = eventRepository.findById(id).orElseThrow();

        existingEvent.setAllEventEntity(
                id,
                eventDTOupdate.getCreator(),
                eventDTOupdate.getEventName(),
                eventDTOupdate.getEventPicture(),
                eventDTOupdate.getEventLocation(),
                eventDTOupdate.getEventDate(),
                eventDTOupdate.getEventShortDescription(),
                eventDTOupdate.getEventLongDescription());

        return eventRepository.save(existingEvent);
    }

    public List<EventModel> findAll (){
        return eventRepository.findAll();
    }
    public EventModel save(EventModel eventModel){
        return eventRepository.save(eventModel);
    }
    //EventProfilePicture upload logic with Exceptions
//    public void uploadEventProfilePicture(String eventId, MultipartFile file) throws IOException {
//        EventModel event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        String uploadDir = "/event-pictures/";
//        String filePath = uploadDir + eventId + "/" + fileName;
//
//        Path uploadPath = Paths.get(uploadDir + eventId);
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        // Copy the file to the target location
//        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
//
//        // Set the file path in the event model
//        event.setEventPicture(filePath);
//
//        // Save the updated event model
//        eventRepository.save(event);
//    }

}

