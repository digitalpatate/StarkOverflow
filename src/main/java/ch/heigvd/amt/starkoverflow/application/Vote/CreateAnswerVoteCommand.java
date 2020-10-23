package ch.heigvd.amt.starkoverflow.application.Vote;


import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class CreateAnswerVoteCommand {
    private String userId;
    private String answerId;

    public Vote createEntity() {
        return new Vote(new UserId(userId), new Answer(new AnswerId(answerId)));
    }
}
