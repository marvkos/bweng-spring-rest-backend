package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Blog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface BlogRepository extends CrudRepository<Blog, UUID> {


    List<Blog> findFirst4ByOrderByCreatedOnDesc();

}
