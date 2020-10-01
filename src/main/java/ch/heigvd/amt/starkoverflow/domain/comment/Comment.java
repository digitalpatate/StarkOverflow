package ch.heigvd.amt.starkoverflow.domain.comment;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Comment implements IEntity {

    private CommentId id;

    private String content;
    private Date creationDate;
    private User author;
    private Commentable commentable;

    public Comment(String content) {
        this.content = content;
        this.creationDate = new Date();
        this.id = new CommentId();
    }
}
