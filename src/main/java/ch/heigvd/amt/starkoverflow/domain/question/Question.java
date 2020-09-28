package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity {

    private QuestionId id;

    private String title;
    private String content;
    private Date creationDate;
    private List<Tag> tags;
    private User author;

/*    public Question(String title, String content) {
        this.id = new QuestionId();
        this.title = title;
        this.content = content;
        this.creationDate = new Date();
    }*/

    //private Collection<Tag> tags;
    // private User author;





}
