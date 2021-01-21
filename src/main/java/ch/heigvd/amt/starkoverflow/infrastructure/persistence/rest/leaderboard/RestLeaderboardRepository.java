package ch.heigvd.amt.starkoverflow.infrastructure.persistence.rest.leaderboard;

import ch.heigvd.amt.starkoverflow.domain.leaderboard.ILeaderboardRepository;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.GamificatorService;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.dto.LeaderBoardDTO;
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
    public Optional<LeaderBoardDTO> findByPointScaleName(String pointScaleName) {
        try {
            LeaderBoardDTO leaderBoardDTOGame = gamificatorService.getLeaderboardByPointScaleName(pointScaleName);
            return Optional.of(leaderBoardDTOGame);
        }catch (NotFoundException e){

            return Optional.empty();
        }
    }
}
