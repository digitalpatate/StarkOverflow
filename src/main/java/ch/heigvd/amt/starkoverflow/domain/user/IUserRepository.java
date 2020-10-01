package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import java.util.Collection;

public interface IUserRepository extends IRepository<User, UserId> {
    Collection<User> find(UserQuery query);
}