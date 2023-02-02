package at.technikum.springrestbackend.service.file;

import at.technikum.springrestbackend.dto.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public interface FileService {

    FileDto upload(MultipartFile fileData);

    FileDto load(UUID id);
}
