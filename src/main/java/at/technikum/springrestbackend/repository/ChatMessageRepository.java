package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.ChatMessageModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends ListCrudRepository<ChatMessageModel, String> {
}
