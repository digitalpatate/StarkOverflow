package ch.heigvd.amt.starkoverflow.application.Event;

import ch.heigvd.amt.starkoverflow.domain.event.Event;
import ch.heigvd.amt.starkoverflow.domain.event.IEventRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@AllArgsConstructor
@Named("EventService")
@ApplicationScoped
public class EventService {

    @Inject
    @Named("RestEventRepository")
    private IEventRepository eventRepository;

    public void triggerEvent(Event event){
        eventRepository.save(event);
    }
}
