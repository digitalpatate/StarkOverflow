package ch.heigvd.amt.starkoverflow.domain.statistic;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Statistic implements IEntity {
    private StatisticId id;
    private String title;
    private String data;
}
