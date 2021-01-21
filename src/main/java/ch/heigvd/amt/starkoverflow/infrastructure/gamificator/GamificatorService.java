package ch.heigvd.amt.starkoverflow.infrastructure.gamificator;

import ch.heigvd.amt.starkoverflow.application.Event.CreateEventCommand;
import ch.heigvd.amt.starkoverflow.application.PointScale.CreatePointScaleCommand;
import ch.heigvd.amt.starkoverflow.application.PointScale.dto.PointScaleDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.CreateRuleCommand;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.dto.LeaderBoardDTO;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
@Named("GamificatorService")
@NoArgsConstructor
public class GamificatorService {

    @Inject @Named("RestService")
    private RestService restService;

    public Object getAllBadges() throws NotFoundException {
        return restService.get("/badges",String.class);
    }

    public void sendEvent(CreateEventCommand createEventCommand){
        restService.post("/events", createEventCommand);
    }

    public LeaderBoardDTO getLeaderboardByPointScaleName(String pointScaleName) throws NotFoundException {
        return (LeaderBoardDTO) restService.get("/leaderboard/" + pointScaleName, LeaderBoardDTO.class);
    }
    public void sendRule(CreateRuleCommand createRuleCommand) {
        restService.post("/rules", createRuleCommand);
    }

    public void sendPointScale(CreatePointScaleCommand createPointScaleCommand) {
        restService.post("/pointScales", createPointScaleCommand);
    }

    public List<PointScaleDTO> getAllPointScales() throws NotFoundException {
        PointScaleDTO[] result = (PointScaleDTO[]) restService.get("/pointScales",PointScaleDTO[].class);

        List<PointScaleDTO> pointScaleDTOS = new LinkedList<>();

        pointScaleDTOS.addAll(Arrays.asList(result));

        return pointScaleDTOS;
    }
}
