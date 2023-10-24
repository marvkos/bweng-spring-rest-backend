package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Specialization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface SpecializationRepository extends CrudRepository<Specialization, UUID> {

    @Override
    List<Specialization> findAll();
}
