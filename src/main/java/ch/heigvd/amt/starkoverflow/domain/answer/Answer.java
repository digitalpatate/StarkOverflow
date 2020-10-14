package ch.heigvd.amt.starkoverflow.domain.answer;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.enterprise.context.NormalScope;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
public class Answer implements IEntity<Answer, AnswerId>, Commentable, Votable {

    private AnswerId id;

    private String content;
    private Date creationDate;
    private UserId author;
    private QuestionId question;
    private boolean approved;


    public Answer(String content) {
        this.content = content;
        this.creationDate = new Date();
        this.id = new AnswerId();
        // Is that a domain related or business ? Or is domain== business
        this.approved = false;
    }


    //TODO complèter la fonction
    @Override
    public Answer deepClone(){
        return null;
    }
}
