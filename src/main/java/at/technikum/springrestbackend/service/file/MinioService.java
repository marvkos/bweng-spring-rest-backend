package at.technikum.springrestbackend.service.file;

import at.technikum.springrestbackend.exception.FileLoadException;
import at.technikum.springrestbackend.exception.FileUploadException;
import at.technikum.springrestbackend.service.file.BucketService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MinioService implements BucketService {

    private final MinioClient minioClient;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void upload(String bucket, String name, InputStream stream, long size, String contentType) {
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(name)
                            .stream(stream, size, -1)
                            .contentType(contentType)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileUploadException();
        }
    }

    @Override
    public InputStream load(String bucket, String name) {
        try {
            InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(name)
                            .build()
            );

            return stream;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileLoadException();
        }
    }
}
