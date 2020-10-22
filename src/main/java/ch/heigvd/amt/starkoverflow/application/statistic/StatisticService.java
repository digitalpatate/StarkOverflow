package ch.heigvd.amt.starkoverflow.application.statistic;

import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@AllArgsConstructor
@Named("StatisticService")
@ApplicationScoped
public class StatisticService {
    @Inject @Named("JdbcUserRepository")
    IUserRepository userRepository;

    public int getNbUsers() {
        return userRepository.getTotalUser();
    }
}
