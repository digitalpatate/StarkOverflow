package ch.heigvd.amt.starkoverflow.infrastructure.gamificator.dto;

import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.UserScoreDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaderBoardDTO {
    private Long total;
    private Long nextPage;
    private Long numberOfPage;
    private List<UserScoreDTO> leaderboard=null;
}
