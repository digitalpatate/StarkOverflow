package ch.heigvd.amt.starkoverflow.application.Reputation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReputationDTO {
    private List<BadgeDTO> badgesReward = null;
    private List<PointRewardDTO> pointsReward = null;
}
