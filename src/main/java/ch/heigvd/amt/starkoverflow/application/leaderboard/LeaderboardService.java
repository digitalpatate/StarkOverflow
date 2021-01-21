package ch.heigvd.amt.starkoverflow.application.leaderboard;

import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.*;
import ch.heigvd.amt.starkoverflow.domain.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.User;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.leaderboard.ILeaderboardRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named("LeaderboardService")
@ApplicationScoped
public class LeaderboardService {
    @Inject
    @Named("RestLeaderboardRepository")
    private ILeaderboardRepository leaderboardRepository;

    @Inject
    @Named("JdbcUserRepository")
    private IUserRepository userRepository;


    public PagableLeaderboardDTO getLeaderBoardFromPointScaleName(String pointScaleName) throws RuntimeException{

        LeaderBoardDTO_game leaderBoardDTOGame = leaderboardRepository.findByPointScaleName(pointScaleName).orElseThrow(() -> new RuntimeException("Leaderboard not found"));
        LeaderboardDTO leaderboardDTO = new LeaderboardDTO();

        for (UserScoreDTO userScoreDTO : leaderBoardDTOGame.getLeaderboard()){
            Optional<User> oUser = userRepository.findById(new UserId(userScoreDTO.getUser().getUuid()));
            if(oUser.isEmpty()){
                ScoreDTO scoreDTO = new ScoreDTO("username",10);
                leaderboardDTO.getScores().add(scoreDTO);
                continue;
            }
            String username = oUser.get().getUsername();
            ScoreDTO scoreDTO = new ScoreDTO(username,userScoreDTO.getScore());
            leaderboardDTO.getScores().add(scoreDTO);
        }

        return new PagableLeaderboardDTO(
                leaderBoardDTOGame.getTotal(),
                leaderBoardDTOGame.getNextPage(),
                leaderBoardDTOGame.getNumberOfPage(),
                leaderboardDTO
        );
    }

}
