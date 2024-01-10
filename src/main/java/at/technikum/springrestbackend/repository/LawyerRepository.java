package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Lawyer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LawyerRepository extends CrudRepository<Lawyer, UUID> {

    @Override
    List<Lawyer> findAll();

}
