package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.EventDTO;
import at.technikum.springrestbackend.model.EventModel;


import java.util.UUID;
public class EventMapper {
    public EventDTO toDTO(EventModel eventModel) {
    //creating a new DTO of Event to assign the values of the Entity to it
    EventDTO newEventDTO = new EventDTO();
    //assigning all Information
    newEventDTO.setAllEventDTO(
            eventModel.getEventId(), eventModel.getUser(),
            eventModel.getEventName(), eventModel.getEventPicture(),
            eventModel.getEventAdress(), eventModel.getEventDate(),
            eventModel.getEventShortDescription(), eventModel.getEventLongDescription());
    return newEventDTO;
}

    public EventModel toEntity(EventDTO eventDTO) {
        //DataBank entry requires the id as a primary key
        if (eventDTO.getEventId() == null) {
            return new EventModel(
                    UUID.randomUUID().toString(),
                    eventDTO.getUser(), eventDTO.getEventName(),
                    eventDTO.getEventPicture(), eventDTO.getEventAdress(),
                    eventDTO.getEventDate(),
                    eventDTO.getEventShortDescription(),
                    eventDTO.getEventLongDescription());
        }
//      ALTERNATIVELY:
//        if (eventDTO.getEventId() == null) {
//            EventModel newEventModel = new EventModel(
//                          UUID.randomUUID().toString(),
//                          ...
//                      );
//            return newEventModel;
//        }
        return new EventModel(
                eventDTO.getEventId(),
                eventDTO.getUser(), eventDTO.getEventName(),
                eventDTO.getEventPicture(), eventDTO.getEventAdress(),
                eventDTO.getEventDate(),
                eventDTO.getEventShortDescription(),
                eventDTO.getEventLongDescription());
    }
}
