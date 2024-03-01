package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.ForumPostModel;
import at.technikum.springrestbackend.model.ForumThreadModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumThreadRepository extends ListCrudRepository<ForumThreadModel, String> {
}
