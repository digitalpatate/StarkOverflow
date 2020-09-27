package ch.heigvd.amt.starkoverflow.application.question.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionDTO {
    private String text;
}
