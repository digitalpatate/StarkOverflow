package ch.heigvd.amt.starkoverflow.domain.statistic;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Statistic implements IEntity<Statistic, StatisticId> {
    private StatisticId id;
    private String title;
    private String data;

}
