package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Appointment;
import at.technikum.springrestbackend.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends CrudRepository<Appointment, UUID> {

    List<Appointment> findByForUser(User user);
}
