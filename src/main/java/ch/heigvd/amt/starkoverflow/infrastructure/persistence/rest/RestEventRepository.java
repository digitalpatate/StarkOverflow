package ch.heigvd.amt.starkoverflow.infrastructure.persistence.rest;

import ch.heigvd.amt.starkoverflow.application.Event.CreateEventCommand;
import ch.heigvd.amt.starkoverflow.domain.event.Event;
import ch.heigvd.amt.starkoverflow.domain.event.IEventRepository;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.GamificatorService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("RestEventRepository")
@NoArgsConstructor
public class RestEventRepository implements IEventRepository {

    @Inject
    @Named("GamificatorService")
    private GamificatorService gamificatorService;

    @Override
    public void save(Event event) {
        CreateEventCommand createEventCommand = CreateEventCommand.builder()
                .userId(event.getUserId().asString())
                .timestamp(event.getTimestamp().toString())
                .type(event.getType())
                .build();

        System.out.println("createEventCommand: " + createEventCommand);

        gamificatorService.sendEvent(createEventCommand);
    }
}
