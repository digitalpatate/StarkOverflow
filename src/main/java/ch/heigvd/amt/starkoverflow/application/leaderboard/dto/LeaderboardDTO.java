package ch.heigvd.amt.starkoverflow.application.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardDTO {
    private List<ScoreDTO> scores = new ArrayList<>();
}
