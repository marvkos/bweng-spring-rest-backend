package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.dto.FileDto;
import at.technikum.springrestbackend.service.file.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> serve(
            @PathVariable UUID id
    ) {
        FileDto fileDto = fileService.load(id);
        MediaType mediaType = MediaType.parseMediaType(fileDto.getContentType());

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(new InputStreamResource(fileDto.getInputStream()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileDto upload(
            @RequestParam MultipartFile file
    ) {
        return fileService.upload(file);
    }
}
