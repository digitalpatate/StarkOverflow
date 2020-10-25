package ch.heigvd.amt.starkoverflow.application.statistic;

import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.IAnswerCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.IQuestionCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
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
    IQuestionRepository questionRepository;

    @Inject @Named("JdbcAnswerRepository")
    IAnswerRepository answerRepository;

    @Inject @Named("JdbcAnswerCommentRepository")
    IAnswerCommentRepository answerCommentRepository;

    @Inject @Named("JdbcQuestionCommentRepository")
    IQuestionCommentRepository questionCommentRepository;

    @Inject @Named("JdbcAnswerVoteRepository")
    IAnswerVoteRepository answerVoteRepository;

    @Inject @Named("JdbcQuestionVoteRepository")
    IQuestionVoteRepository questionVoteRepository;


    public int getNbUsers() {
        return userRepository.getTotalUser();
    }

    public int getNbQuestions() {
        return questionRepository.getTotalQuestion();
    }

    public int getNbAnswers() {
        return answerRepository.getTotalAnswer();
    }

    public int getNbComments() {
        return answerCommentRepository.getTotalAnswerComment() +
                questionCommentRepository.getTotalQuestionCommentRepository();
    }

    public int getNbVotes() {
        return answerVoteRepository.getTotalAnswerVoteRepository() +
                questionVoteRepository.getTotalQuestionVoteRepository();
    }
}
