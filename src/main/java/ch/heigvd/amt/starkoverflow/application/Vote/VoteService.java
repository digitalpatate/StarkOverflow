package ch.heigvd.amt.starkoverflow.application.Vote;

import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
@Named("VoteService")
public class VoteService {

    @Inject @Named("JdbcVoteRepository")
    private IAnswerVoteRepository answerVoteRepository;
    @Inject @Named("JdbcAnswerRepository")
    private IAnswerRepository answerRepository;

    public Vote createAnswerVote(CreateAnswerVoteCommand command) throws NotFoundException {
        Vote vote = command.createEntity();
        UserId userId = new UserId(command.getUserId());
        AnswerId answerId = new AnswerId(command.getAnswerId());

        Vote userVote = answerVoteRepository.userVoteOnAnswer(userId, answerId);
        if(userVote != null) {
            // delete his vote
            answerVoteRepository.remove(userVote.getId());
            return null;
        } else if (answerRepository.findById(answerId).map(answer -> answer.getUserId() == userId).orElse(false)) {
            throw new IllegalArgumentException("User can't vote on his own answer");
        } else {
            // insert his vote
            return answerVoteRepository.save(vote);
        }
    }
}
