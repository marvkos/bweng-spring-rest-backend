package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.UserModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<UserModel, String> {
    Optional<UserModel> findByUsername(String username);
}
