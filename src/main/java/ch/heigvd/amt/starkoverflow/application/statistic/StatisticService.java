package ch.heigvd.amt.starkoverflow.application.statistic;

import ch.heigvd.amt.starkoverflow.application.statistic.dto.StatisticDTO;
import ch.heigvd.amt.starkoverflow.application.statistic.dto.StatisticsDTO;
import ch.heigvd.amt.starkoverflow.domain.statistic.IStatisticRepository;
import ch.heigvd.amt.starkoverflow.domain.statistic.Statistic;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Named("StatisticService")
@ApplicationScoped
public class StatisticService {
    @Inject @Named("InMemoryStatisticRepository")
    private IStatisticRepository statisticRepository;



    public StatisticsDTO getStatistic(StatisticQuery query) {
        Collection<Statistic> statistics = statisticRepository.find(query);

        List<StatisticDTO> statisticsDTO = statistics.stream().map(statistic -> StatisticDTO.builder().build()).collect(Collectors.toList());

        return StatisticsDTO.builder().statistics(statisticsDTO).build();
    }
}
