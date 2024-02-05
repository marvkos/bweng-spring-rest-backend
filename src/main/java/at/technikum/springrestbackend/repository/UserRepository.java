package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.UserModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<UserModel, String> {
}
