package at.technikum.springrestbackend.bean;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioClientBean {

    @Value("${bucket.access.host}")
    String host;

    @Value("${bucket.access.port}")
    int port;

    @Value("${bucket.access.key}")
    String accessKey;

    @Value("${bucket.access.secret}")
    String accessSecret;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(host, port, false)
                .credentials(accessKey, accessSecret)
                .build();
    }
}
