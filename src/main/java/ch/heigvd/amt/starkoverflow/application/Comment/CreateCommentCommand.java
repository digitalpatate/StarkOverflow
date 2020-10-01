package ch.heigvd.amt.starkoverflow.application.Comment;

import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;

public class CreateCommentCommand {

    private String commentableId;

    private String content;

    public Comment createEntity() {
        return new Comment(content);
    }
}
