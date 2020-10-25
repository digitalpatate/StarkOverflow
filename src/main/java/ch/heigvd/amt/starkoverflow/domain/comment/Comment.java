package ch.heigvd.amt.starkoverflow.domain.comment;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.Data;

import java.util.Date;

@Data
public class Comment implements IEntity{

    private CommentId id;
    private String content;
    private Date creationDate;
    private UserId author;
    private Id commentableId;

    public Comment(String content) {
        this.content = content;
        this.creationDate = new Date();
        this.id = new CommentId();
    }

    public Comment(String content, UserId author, ICommentable commentable) {
        this.content = content;
        this.creationDate = new Date();
        this.author = author;
        this.id = new CommentId();
        this.commentableId = commentable.getId();
    }

    public Comment(CommentId id, String content, UserId author, ICommentable commentable) {
        this.content = content;
        this.creationDate = new Date();
        this.author = author;
        this.id = id;
        this.commentableId = commentable.getId();
    }
}
