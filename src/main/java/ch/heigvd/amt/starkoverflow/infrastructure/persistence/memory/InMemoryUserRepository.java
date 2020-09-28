package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import java.util.Collection;
import java.util.Optional;

public class InMemoryUserRepository implements IUserRepository {
    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void remove(UserId id) {

    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.empty();
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}
