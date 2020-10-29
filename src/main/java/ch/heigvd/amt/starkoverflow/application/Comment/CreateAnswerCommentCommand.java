package ch.heigvd.amt.starkoverflow.application.Comment;

import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.Builder;

@Builder(toBuilder = true)
public class CreateAnswerCommentCommand {
    private String answerId;
    private String content;
    private String author;

    public Comment createEntity() {
        return new Comment(content, new UserId(author), new Answer(new AnswerId(answerId)));
    }
}