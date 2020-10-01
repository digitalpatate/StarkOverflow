package ch.heigvd.amt.starkoverflow.domain.statistic;

import ch.heigvd.amt.starkoverflow.application.statistic.StatisticQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;

import java.util.Collection;

public interface IStatisticRepository extends IRepository<Statistic, StatisticId> {
    public Collection<Statistic> find(StatisticQuery query);
}
