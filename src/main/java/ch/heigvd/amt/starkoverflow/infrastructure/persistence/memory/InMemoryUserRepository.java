package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository extends InMmemoryRepository<User,UserId> implements IUserRepository {

    @Override
    public User save(User entity) {
        synchronized (entity.getUsername()){
            if(!findByUsername(entity.getUsername()).isEmpty()){
                //throw new SQLIntegrityConstraintViolationException("");
            }
            super.save(entity);
        }
        return entity;
    }


    public Optional<User> findByUsername(String username) {
        List<User> matchingEntities = findAll().stream()
            .filter(p -> p.getUsername().equals(username))
            .collect(Collectors.toList());

        if(matchingEntities.size() < 1){
            return Optional.empty();
        }

        if(matchingEntities.size() > 1){
            //throw new DataCorruptionException("");
        }

        return Optional.of(matchingEntities.get(0).deepClone());
    }

    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }
}
