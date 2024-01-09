package at.technikum.springrestbackend.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.GetObjectArgs;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class FileUploaderService {

    private final MinioClient minioClient;

    @Autowired
    public FileUploaderService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void uploadFile(String bucketName, String objectName, String filename)
            throws IOException, MinioException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .filename(filename)
                            .build());
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in file upload: Security error", e);
        }
    }

    public Resource downloadFile(String bucketName, String objectName)
            throws IOException, MinioException {
        try {
            InputStream fileStream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build());
            return new InputStreamResource(fileStream);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in file download: Security error", e);
        }
    }
}
