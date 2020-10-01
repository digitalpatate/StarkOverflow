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

public class InMemoryStatisticRepository implements IStatisticRepository {

    private Map<StatisticId, Statistic> store = new ConcurrentHashMap<>();

    @Override
    public void save(Statistic statistic) {
        store.put(statistic.getId(), statistic);
    }

    @Override
    public void remove(StatisticId statisticId) {
        store.remove(statisticId);
    }

    @Override
    public Optional<Statistic> findById(StatisticId statisticId) {
        Statistic statistic = store.get(statisticId);

        if(statistic == null){
            return Optional.empty();
        }

        Statistic copyStatistic = statistic.toBuilder().build();
        return Optional.of(copyStatistic);
    }

    @Override
    public Collection<Statistic> findAll() {
        return store.values().stream()
                .map(question -> question.toBuilder().build())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Statistic> find(StatisticQuery query) {
        return findAll();
    }
}
