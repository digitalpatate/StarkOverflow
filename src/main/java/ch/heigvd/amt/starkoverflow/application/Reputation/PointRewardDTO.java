package ch.heigvd.amt.starkoverflow.application.Reputation;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointRewardDTO {
    private String pointScaleName;

    private Integer nbPoint;

    private String pointScaleDescription;
}
