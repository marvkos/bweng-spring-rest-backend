package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.EventModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends ListCrudRepository<EventModel, String>{

    List<EventModel> findByLocation(String eventAdress); //search event by location

}
