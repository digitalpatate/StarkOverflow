package ch.heigvd.amt.starkoverflow.application.leaderboard.dto;

import lombok.*;

@AllArgsConstructor
@Data
public class PagableLeaderboardDTO {

    private long total;
    private long nextPage;
    private long numberOfPage;
    private LeaderboardDTO leaderboard;


}
