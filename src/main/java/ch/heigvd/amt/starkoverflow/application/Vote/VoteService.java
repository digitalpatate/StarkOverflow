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
@NoArgsConstructor
@AllArgsConstructor
public class VoteService {

    @Inject
    private IVoteRepository voteRepository;
    @Inject
    private IUserRepository userRepository;
    @Inject
    private IQuestionRepository questionRepository;



    public Vote createVote(CreateVoteCommand command) throws NotFoundException {
        Vote vote = command.createEntity();
        User user = userRepository.findById(new UserId(command.getUserId())).orElseThrow(NotFoundException::new);
        Question question = questionRepository.findById(new QuestionId(command.getVotableId())).orElseThrow(NotFoundException::new);
        vote.setUser(user);
        vote.setVotable(question);
        return voteRepository.save(vote);
    }
}
