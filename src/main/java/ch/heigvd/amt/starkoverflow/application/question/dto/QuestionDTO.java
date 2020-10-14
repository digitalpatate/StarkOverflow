package ch.heigvd.amt.starkoverflow.application.question.dto;

import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswersDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QuestionDTO {
    private String id;
    private String title;
    private String content;
    private String creationDate;
    private TagsDTO tags;
    private AnswersDTO answers;
}
