package ch.heigvd.amt.starkoverflow.application.Answer.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class AnswersDTO {
    @Singular
    private List<AnswerDTO> answers;
}
