package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Vote implements IEntity<Vote, VoteId> {
    private VoteId id;
    private Votable votable;
    private User user;

    public Vote() {
        this.id = new VoteId();
    }
    

    //TODO complèter la fonction
    @Override
    public Vote deepClone(){
        return null;
    }
}
