package ch.heigvd.amt.starkoverflow.domain.statistic;

import ch.heigvd.amt.starkoverflow.domain.Id;

import java.util.UUID;

public class StatisticId extends Id {
    public StatisticId() {
        super();
    }

    public StatisticId(String id) {
        super(id);
    }

    public StatisticId(UUID id) {
        super(id);
    }
}
