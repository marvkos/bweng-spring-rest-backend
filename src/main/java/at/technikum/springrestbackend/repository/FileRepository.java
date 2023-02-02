package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
}
