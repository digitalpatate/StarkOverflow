package ch.heigvd.amt.starkoverflow.domain.answer;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class Answer implements IEntity {

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
}
