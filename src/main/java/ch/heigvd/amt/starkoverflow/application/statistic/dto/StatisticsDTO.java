package ch.heigvd.amt.starkoverflow.application.statistic.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class StatisticsDTO {
    @Singular
    private List<StatisticDTO> statistics;
}
