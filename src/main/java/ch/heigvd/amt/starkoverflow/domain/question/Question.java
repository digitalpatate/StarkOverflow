package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class Question implements IEntity<Question,QuestionId>, Commentable, Votable {

    private QuestionId id;

    private String title;
    private String content;
    private Date creationDate;
    private List<Tag> tags;
    private UserId author;

   public Question(String title, String content, UserId userId) {
       this.id = new QuestionId();
       this.title = title;
       this.content = content;
       this.author = userId;
       this.creationDate = new Date();
   }

    public Question(QuestionId id, String title, String content, Date creationDate, UserId author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.author = author;
    }

    //TODO complèter la fonction
    @Override
    public Question deepClone(){
        return null;
    }
}
