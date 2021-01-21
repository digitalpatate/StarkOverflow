package ch.heigvd.amt.starkoverflow.infrastructure.persistence.rest.leaderboard;

import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.LeaderBoardDTO_game;
import ch.heigvd.amt.starkoverflow.domain.leaderboard.ILeaderboardRepository;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.GamificatorService;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@ApplicationScoped
@Named("RestLeaderboardRepository")
@NoArgsConstructor
public class RestLeaderboardRepository implements ILeaderboardRepository {

    @Inject
    @Named("GamificatorService")
    private GamificatorService gamificatorService;

    @Override
    public Optional<LeaderBoardDTO_game> findByPointScaleName(String pointScaleName) {
        try {
            LeaderBoardDTO_game leaderBoardDTOGame = gamificatorService.getLeaderboardByPointScaleName(pointScaleName);
            return Optional.of(leaderBoardDTOGame);
        }catch (RuntimeException e){
            return Optional.empty();
        }
    }
}
