package ch.heigvd.amt.starkoverflow.application.statistic;

import ch.heigvd.amt.starkoverflow.application.statistic.dto.StatisticDTO;
import ch.heigvd.amt.starkoverflow.application.statistic.dto.StatisticsDTO;
import ch.heigvd.amt.starkoverflow.domain.statistic.IStatisticRepository;
import ch.heigvd.amt.starkoverflow.domain.statistic.Statistic;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticService {
    private IStatisticRepository statisticRepository;

    public StatisticService(IStatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public StatisticsDTO getStatistic(StatisticQuery query) {
        Collection<Statistic> statistics = statisticRepository.find(query);

        List<StatisticDTO> statisticsDTO = statistics.stream().map(statistic -> StatisticDTO.builder().build()).collect(Collectors.toList());

        return StatisticsDTO.builder().statistics(statisticsDTO).build();
    }
}
