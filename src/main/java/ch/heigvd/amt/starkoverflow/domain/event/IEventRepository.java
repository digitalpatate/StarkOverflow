package ch.heigvd.amt.starkoverflow.domain.event;

public interface IEventRepository {

    void save(Event event);
}
