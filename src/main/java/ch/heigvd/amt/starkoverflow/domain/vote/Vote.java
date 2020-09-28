package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Vote implements IEntity {
    private VoteId voteId;
    private Votable votable;
    private User user;
}
