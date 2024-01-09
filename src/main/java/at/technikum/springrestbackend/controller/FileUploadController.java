
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

    @GetMapping("/api/files/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam String bucketName, @RequestParam String objectName) {
        try {
            System.out.println("Downloading file from bucket: " + bucketName + " with name: " + objectName);
            InputStreamResource fileResource = (InputStreamResource) fileUploaderService.downloadFile(bucketName, objectName);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + objectName + "\"")
                    .body(fileResource);
        } catch (Exception e) {
            e.printStackTrace(); // Log the stack trace
            return ResponseEntity.internalServerError().body(null);
        }
    }

}


