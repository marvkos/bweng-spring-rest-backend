package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.GeneralAvailability;
import at.technikum.springrestbackend.model.Lawyer;
import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface GeneralAvailabilityRepository extends CrudRepository<GeneralAvailability, UUID> {

    List<GeneralAvailability> findAllForLawyer(Lawyer lawyer);

    List<GeneralAvailability> findByDayAndForLawyer(DayOfWeek day, Lawyer lawyer);
}
