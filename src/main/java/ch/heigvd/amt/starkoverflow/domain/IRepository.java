package ch.heigvd.amt.starkoverflow.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T extends IEntity, U extends Id> {

    T save(T entity);
    void remove(U id);
    Optional<T> findById(U id);
    Collection<T> findAll();
}
