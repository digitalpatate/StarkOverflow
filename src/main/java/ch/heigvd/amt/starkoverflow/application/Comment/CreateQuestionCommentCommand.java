package ch.heigvd.amt.starkoverflow.application.Comment;

import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.Builder;

@Builder(toBuilder = true)
public class CreateQuestionCommentCommand {
    private String questionId;
    private String content;
    private String author;

    public Comment createEntity() {
        return new Comment(content, new UserId(author), new Question(new QuestionId(questionId)));
    }
}
