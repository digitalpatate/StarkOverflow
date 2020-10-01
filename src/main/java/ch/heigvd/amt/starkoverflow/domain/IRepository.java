package ch.heigvd.amt.starkoverflow.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<T extends IEntity, U extends Id> {

    //TODO return the IEntity
    public void save(T entity);
    public void remove(U id);
    public Optional<T> findById(U id);
    public Collection<T> findAll();
}
