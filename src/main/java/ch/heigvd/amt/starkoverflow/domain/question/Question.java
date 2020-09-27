package ch.heigvd.amt.starkoverflow.domain.question;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity {

    private QuestionId id;
    private String title;
    private String content;
    private Date creationDate;

    //private Collection<Tag> tags;
    // private User author;





}
