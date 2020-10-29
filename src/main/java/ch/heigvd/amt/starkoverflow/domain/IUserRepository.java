package ch.heigvd.amt.starkoverflow.domain;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;

import java.util.Collection;
import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {
    Collection<User> find(UserQuery query);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    int getTotalUser();
}
