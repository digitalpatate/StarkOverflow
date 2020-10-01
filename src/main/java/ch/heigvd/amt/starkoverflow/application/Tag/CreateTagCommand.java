package ch.heigvd.amt.starkoverflow.application.Tag;


import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class CreateTagCommand {

    private String name;
    private String color;

    public Tag createEntity() {

        return new Tag(name,color);

    }
}
