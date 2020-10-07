package ch.heigvd.amt.starkoverflow.application.Vote;


import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class CreateVoteCommand {

    private String userId;
    private String votableId;
    private String votableType;

    public Vote createEntity() {
        return new Vote();
    }
}
