package ch.heigvd.amt.starkoverflow.domain;

import ch.heigvd.amt.starkoverflow.domain.question.Question;

import javax.ejb.Stateless;
import java.util.Collection;
import java.util.Optional;

public interface IRepository<T extends IEntity, U extends Id> {


    //TODO return the IEntity
    public T save(T entity);
    public void remove(U id);
    public Optional<T> findById(U id);
    public Collection<T> findAll();
}
