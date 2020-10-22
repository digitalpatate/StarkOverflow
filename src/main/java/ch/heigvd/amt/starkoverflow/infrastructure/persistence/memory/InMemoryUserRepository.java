package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("InMemoryUserRepository")
public class InMemoryUserRepository extends InMemoryRepository<User,UserId> implements IUserRepository {

    @Override
    public User save(User entity) {
        synchronized (entity.getUsername()){
            if(!findByEmail(entity.getUsername()).isEmpty()){
                //throw new SQLIntegrityConstraintViolationException("");
            }
            super.save(entity);
        }
        return entity;
    }


    public Optional<User> findByEmail(String email) {
        List<User> matchingEntities = findAll().stream()
            .filter(p -> p.getEmail().equals(email))
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
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public int getTotalUser() {
        return 0;
    }

    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }
}
