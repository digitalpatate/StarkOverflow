package ch.heigvd.amt.starkoverflow.infrastructure.gamificator;

import ch.heigvd.amt.starkoverflow.application.Event.CreateEventCommand;
import ch.heigvd.amt.starkoverflow.application.PointScale.CreatePointScaleCommand;
import ch.heigvd.amt.starkoverflow.application.Rule.CreateRuleCommand;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named("GamificatorService")
@NoArgsConstructor
public class GamificatorService {

    @Inject @Named("RestService")
    private RestService restService;

    public Object getAllBadges() {
        return restService.get("/badges",String.class);
    }

    public void sendEvent(CreateEventCommand createEventCommand){
        restService.post("/events", createEventCommand);
    }

    public void sendRule(CreateRuleCommand createRuleCommand) {
        restService.post("/rules", createRuleCommand);
    }

    public void sendPointScale(CreatePointScaleCommand createPointScaleCommand) {
        restService.post("/pointScales", createPointScaleCommand);
    }
}
