package ch.heigvd.amt.starkoverflow.application.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LeaderBoardDTO_game {
    private Long total;
    private Long nextPage;
    private Long numberOfPage;
    private List<UserScoreDTO> leaderboard=null;
}
