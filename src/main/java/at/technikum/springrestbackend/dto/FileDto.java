package at.technikum.springrestbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class FileDto {

    private UUID id;

    private String originalFileName;

    private String contentType;

    private Date created;

    @JsonIgnore
    private InputStream inputStream;
}
