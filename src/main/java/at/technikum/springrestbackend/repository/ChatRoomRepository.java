package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.ChatMessageModel;
import at.technikum.springrestbackend.model.ChatRoomModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends ListCrudRepository<ChatRoomModel, String> {
}
