package ch.heigvd.amt.starkoverflow.application.question.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class QuestionsDTO {
    @Singular
    private List<QuestionDTO> questions;
}
