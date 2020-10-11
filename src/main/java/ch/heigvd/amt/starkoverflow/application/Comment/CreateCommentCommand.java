package ch.heigvd.amt.starkoverflow.application.Comment;

import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import lombok.Builder;

@Builder(toBuilder = true)
public class CreateCommentCommand {

    private String commentableId;

    private String content;

    private String author;

    public Comment createEntity() {
        return new Comment(content);
    }
}
