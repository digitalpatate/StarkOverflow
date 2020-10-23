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

    @Inject @Named("JdbcQuestionRepository")
    IUserRepository questionRepository;

    @Inject @Named("JdbcAnswerRepository")
    IUserRepository answerRepository;

    @Inject @Named("JdbcCommentRepository")
    IUserRepository commentRepository;


    public int getNbUsers() {
        return userRepository.getTotalUser();
    }

    public int getNbQuestions() {
        return 0;
    }

    public int getNbAnswers() {
        return 0; // TODO
    }

    public int getNbComments() {
        return 0; // TODO
    }

    public int getNbVotes() {
        return 0; // TODO
    }
}
