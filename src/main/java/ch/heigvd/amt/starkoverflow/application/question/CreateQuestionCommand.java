package ch.heigvd.amt.starkoverflow.application.question;


import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class CreateQuestionCommand {

    private String title;
    private String content;

    public Question createEntity() {
        return Question.builder()
                .id(new QuestionId())
                .title(title)
                .content(content)
                .creationDate(new Date())
                .build();
    }
}
