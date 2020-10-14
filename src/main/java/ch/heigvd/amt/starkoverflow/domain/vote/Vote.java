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
    private UserId user_id;

    public Vote() {
        this.id = new VoteId();
    }

    public Vote(VoteId vote_id, UserId user_id ) {
        this.id = vote_id;
        this.user_id = user_id;
    }
    

    //TODO complèter la fonction
    @Override
    public Vote deepClone(){
        return null;
    }
}
