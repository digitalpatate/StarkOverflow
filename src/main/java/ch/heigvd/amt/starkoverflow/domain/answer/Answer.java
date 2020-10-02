package ch.heigvd.amt.starkoverflow.domain.answer;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Builder;
import lombok.Data;

import javax.enterprise.context.NormalScope;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Answer implements IEntity<Answer, AnswerId>, Commentable {

    private AnswerId id;

    private String content;
    private Date creationDate;
    private User author;
    private Question question;
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
