package at.technikum.springrestbackend.service.file;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public interface BucketService {

    void upload(String bucket, String name, InputStream stream, long size, String contentType);

    InputStream load(String bucket, String name);
}
