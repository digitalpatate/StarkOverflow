package ch.heigvd.amt.starkoverflow.application.leaderboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserScoreDTO {
    private UserDTO user;

    private Integer score;
}
