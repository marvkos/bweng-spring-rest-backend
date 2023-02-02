package at.technikum.springrestbackend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class MessageDto {

    private UUID id;

    @NotBlank
    @Length(max = 280)
    private String text;

    private String image;

    private Date created;
}
