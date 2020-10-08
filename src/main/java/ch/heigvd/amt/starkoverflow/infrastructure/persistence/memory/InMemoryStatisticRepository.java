package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.statistic.StatisticQuery;
import ch.heigvd.amt.starkoverflow.domain.statistic.IStatisticRepository;
import ch.heigvd.amt.starkoverflow.domain.statistic.Statistic;
import ch.heigvd.amt.starkoverflow.domain.statistic.StatisticId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;
@ApplicationScoped
@Named("InMemoryStatisticRepository")
public class InMemoryStatisticRepository extends InMemoryRepository<Statistic,StatisticId> implements IStatisticRepository {


    @Override
    public Collection<Statistic> find(StatisticQuery query) {
        return null;
    }
}
