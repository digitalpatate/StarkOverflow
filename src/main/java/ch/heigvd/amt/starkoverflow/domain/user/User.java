package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class User implements IEntity {
    public String email;
    public String password;
    public String profilePictureURL;
    public String firstname;
    public String lastname;
}
