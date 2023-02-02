package at.technikum.springrestbackend.service.file;

import at.technikum.springrestbackend.dto.FileDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.exception.FileUploadException;
import at.technikum.springrestbackend.model.File;
import at.technikum.springrestbackend.repository.FileRepository;
import org.modelmapper.ModelMapper;
import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class BucketFileService implements FileService {

    @Value("${bucket.access.name}")
    private String bucketName;

    private final FileRepository fileRepository;

    private final BucketService bucketService;

    private final ModelMapper modelMapper;

    public BucketFileService(FileRepository fileRepository, BucketService bucketService, ModelMapper modelMapper) {
        this.fileRepository = fileRepository;
        this.bucketService = bucketService;
        this.modelMapper = modelMapper;
    }

    public FileDto upload(MultipartFile fileData) {
        File file = new File();
        file.setOriginalFileName(Encode.forUriComponent(fileData.getOriginalFilename()));
        file.setContentType(fileData.getContentType());
        file = fileRepository.save(file);

        try {
            bucketService.upload(
                    bucketName,
                    file.getId().toString(),
                    fileData.getInputStream(),
                    fileData.getSize(),
                    file.getContentType()
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileUploadException();
        }

        return toDto(file);
    }

    public FileDto load(UUID id) {
        File file = fileRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        InputStream stream = bucketService.load(bucketName, file.getId().toString());

        FileDto fileDto = toDto(file);
        fileDto.setInputStream(stream);

        return fileDto;
    }

    private FileDto toDto(File file) {
        return modelMapper.map(file, FileDto.class);
    }
}
