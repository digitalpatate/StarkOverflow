package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Vote implements IEntity<Vote, VoteId> {
    private VoteId id;
    private AnswerId answerId;
    private UserId userId;

    public Vote() {
        this.id = new VoteId();
    }

    public Vote(UserId userId, AnswerId answerId ) {
        this.id = new VoteId();
        this.answerId = answerId;
        this.userId = userId;
    }

    public Vote(VoteId voteId, UserId userId, AnswerId answerId ) {
        this.id = voteId;
        this.answerId = answerId;
        this.userId = userId;
    }

    //TODO complèter la fonction
    @Override
    public Vote deepClone(){
        return null;
    }
}
