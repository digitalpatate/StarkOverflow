package ch.heigvd.amt.starkoverflow.infrastructure.gamificator;

import ch.heigvd.amt.starkoverflow.application.Event.CreateEventCommand;
import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.LeaderBoardDTO_game;
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

    public LeaderBoardDTO_game getLeaderboardByPointScaleName(String pointScaleName){
        return (LeaderBoardDTO_game) restService.get("/leaderboard/"+pointScaleName, LeaderBoardDTO_game.class);
    }
}
