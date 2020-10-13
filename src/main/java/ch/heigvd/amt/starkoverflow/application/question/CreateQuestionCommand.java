package ch.heigvd.amt.starkoverflow.application.question;

import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Builder;
import lombok.Getter;
import java.util.ArrayList;

@Builder(toBuilder = true)
@Getter
public class CreateQuestionCommand {

    private String title;
    private String content;
    private String userId;
    private ArrayList<Tag> tags;

    public Question createEntity() {
        return new Question(title, content, new UserId(userId));
    }
}
