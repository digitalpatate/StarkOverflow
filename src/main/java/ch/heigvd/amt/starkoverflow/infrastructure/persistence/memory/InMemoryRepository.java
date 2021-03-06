package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T extends IEntity,U extends Id> {



    private Map<Id, T> store = new ConcurrentHashMap<>();
    public T save(T entity) {
        store.put(entity.getId(),entity);

        return entity;
    }
    public void remove(U id) {
        store.remove(id);
    }

    public Optional<T> findById(U id) {
        T entity = store.get(id);
        if(entity == null){
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    public Collection<T> findAll() {
        return new ArrayList<>(store.values());
    }


}
