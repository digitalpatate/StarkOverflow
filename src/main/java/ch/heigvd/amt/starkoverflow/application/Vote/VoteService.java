package ch.heigvd.amt.starkoverflow.application.Vote;


import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@AllArgsConstructor
public class VoteService {

    @Inject @Named("JdbcVoteRepository")
    private IVoteRepository voteRepository;
    @Inject @Named("JdbcUserRepository")
    private IUserRepository userRepository;
    @Inject @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;


    public Vote createVote(CreateVoteCommand command) throws NotFoundException {
        Vote vote = command.createEntity();
        User user = userRepository.findById(new UserId(command.getUserId())).orElseThrow(() -> new NotFoundException("The user with this id does not exist"));
        //FIXME: Should use the commentable_votables registry
        Question question = questionRepository.findById(new QuestionId(command.getVotableId())).orElseThrow(() -> new NotFoundException("The votable with this id does not exist"));
        vote.setUser(user);
        vote.setVotable(question);

        return voteRepository.save(vote);
    }
}
