package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import java.util.Collection;

public class InMemoryUserRepository extends InMemoryRepository<User,UserId> implements IUserRepository {


    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }

}
