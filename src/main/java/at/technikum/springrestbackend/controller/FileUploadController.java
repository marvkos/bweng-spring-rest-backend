
package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.service.FileUploaderService;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import java.io.ByteArrayInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
public class FileUploadController {

    private final FileUploaderService fileUploaderService;

    @Autowired
    public FileUploadController(FileUploaderService fileUploaderService) {
        this.fileUploaderService = fileUploaderService;
    }

    @PostMapping("/api/files/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("bucketName") String bucketName,
                                             @RequestParam("objectName") String objectName) {
        try {
            String tempFileName = System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename();
            file.transferTo(new java.io.File(tempFileName));
            fileUploaderService.uploadFile(bucketName, objectName, tempFileName);
            return ResponseEntity.ok("File uploaded successfully as " + objectName);
        } catch (IOException | MinioException e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }


    @GetMapping("/api/files/download/image/{bucketName}/{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String bucketName, @PathVariable String fileName) {
        try {
            byte[] data = fileUploaderService.downloadImage(bucketName, fileName);
            InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(data));

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // or determine the content type dynamically
                    .body(resource);
        } catch (IOException | MinioException e) {
            return ResponseEntity.status(500).body(new InputStreamResource(new ByteArrayInputStream(("Error downloading image: " + e.getMessage()).getBytes())));
        }
    }

}