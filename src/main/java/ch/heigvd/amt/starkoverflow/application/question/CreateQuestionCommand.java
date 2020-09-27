package ch.heigvd.amt.starkoverflow.application.question;


import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import lombok.Builder;

@Builder(toBuilder = true)
public class CreateQuestionCommand {
    private QuestionsDTO questionsDTO;
}
