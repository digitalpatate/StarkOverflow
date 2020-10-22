package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import javax.ejb.Stateless;
import java.util.Collection;
import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {
    Collection<User> find(UserQuery query);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    int getTotalUser();
}
