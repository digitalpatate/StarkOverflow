package ch.heigvd.amt.starkoverflow.application.Answer;

import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder(toBuilder = true)
public class CreateAnswerCommand  {
    private String content;
    private String questionId;
    private String userId;

    public Answer createEntity() {
        return new Answer(new AnswerId(), content, new UserId(userId), new QuestionId(questionId));
    }
}
