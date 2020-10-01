package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.statistic.StatisticQuery;
import ch.heigvd.amt.starkoverflow.domain.statistic.IStatisticRepository;
import ch.heigvd.amt.starkoverflow.domain.statistic.Statistic;
import ch.heigvd.amt.starkoverflow.domain.statistic.StatisticId;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryStatisticRepository extends InMmemoryRepository<Statistic,StatisticId> implements IStatisticRepository {


    @Override
    public Collection<Statistic> find(StatisticQuery query) {
        return null;
    }
}
