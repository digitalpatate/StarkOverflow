package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Question implements IEntity,Votable,Commentable {

    private QuestionId id;

    private String title;
    private String content;
    private Date creationDate;
    private List<TagId> tags;
    private UserId author;

    public Question(QuestionId questionId) {
        this.id = questionId;
    }

    public Question(String title, String content, UserId userId) {
       this.id = new QuestionId();
       this.title = title;
       this.content = content;
       this.author = userId;
   }

    public Question(QuestionId id, String title, String content, Date creationDate, UserId author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.author = author;
    }
}
