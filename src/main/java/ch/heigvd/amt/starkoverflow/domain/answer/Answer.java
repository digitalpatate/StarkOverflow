package ch.heigvd.amt.starkoverflow.domain.answer;

import ch.heigvd.amt.starkoverflow.domain.Commentable;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Answer implements IEntity, Votable,Commentable {
    private AnswerId id;
    private String content;
    private Date creationDate;
    private UserId userId;
    private QuestionId questionId;
    private boolean approved;

    public Answer(AnswerId id) {
        this.id = id;
    }

    public Answer(String content) {
        this.content = content;
        this.creationDate = new Date();
        this.id = new AnswerId();
        // Is that a domain related or business ? Or is domain== business
        this.approved = false;
    }

    public Answer(AnswerId id, String content, UserId userId, QuestionId questionId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.questionId = questionId;
    }

}
