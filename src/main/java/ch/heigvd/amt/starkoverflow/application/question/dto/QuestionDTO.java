package ch.heigvd.amt.starkoverflow.application.question.dto;

import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class QuestionDTO {
    private String id;
    private String title;
    private String content;
    private String creationDate;
    //private AuthorDTO author;

}
