package ch.heigvd.amt.starkoverflow.application.statistic.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StatisticDTO {
    private String id;
    private String title;
    private String data;
}
