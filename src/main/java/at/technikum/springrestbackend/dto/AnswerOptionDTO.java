package at.technikum.springrestbackend.dto;

import at.technikum.springrestbackend.model.Answer;
import at.technikum.springrestbackend.model.Question;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerOptionDTO {
    private Long id;
    private Answer answer;
    private boolean isCorrect;
    private Question question;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
