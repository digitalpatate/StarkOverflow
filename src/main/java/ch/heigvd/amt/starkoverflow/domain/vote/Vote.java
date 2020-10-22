package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.Data;

@Data
public class Vote implements IEntity<Vote, VoteId> {
    private VoteId id;
    private Id votableId;
    private UserId userId;

    public Vote() {
        this.id = new VoteId();
    }

    public Vote(UserId userId, Votable votable) {
        this.id = new VoteId();
        this.votableId = votable.getId();
        this.userId = userId;
    }

    public Vote(VoteId voteId, UserId userId, Votable votable) {
        this.id = voteId;
        this.votableId = votable.getId();
        this.userId = userId;
    }

    //TODO complèter la fonction
    @Override
    public Vote deepClone(){
        return null;
    }
}
