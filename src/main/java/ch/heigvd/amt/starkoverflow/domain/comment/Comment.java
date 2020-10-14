package ch.heigvd.amt.starkoverflow.domain.comment;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Comment implements IEntity<Comment,CommentId> {

    private CommentId id;

    private String content;
    private Date creationDate;
    private User author;
    private AnswerId answerId;

    public Comment(String content) {
        this.content = content;
        this.creationDate = new Date();
        this.id = new CommentId();
    }

    public Comment(CommentId id, String content) {
        this.content = content;
        this.creationDate = new Date();
        this.id = id;
    }

    //TODO complèter la fonction
    @Override
    public Comment deepClone(){
        return null;
    }
}
