package ch.heigvd.amt.starkoverflow.application.question;

import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class CreateQuestionCommand {

    private String title;
    private String content;
    private String userId;

    public Question createEntity() {
        return new Question(title, content, new UserId(userId));
    }
}
