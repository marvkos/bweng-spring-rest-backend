package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.ForumPostModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends ListCrudRepository<ForumPostModel, String> {
}
