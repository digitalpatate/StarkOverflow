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

public class VoteService {

    private IVoteRepository voteRepository;
    private IUserRepository userRepository;
    private IQuestionRepository questionRepository;

    public VoteService(IVoteRepository voteRepository, IUserRepository userRepository, IQuestionRepository questionRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public Vote createVote(CreateVoteCommand command) throws NotFoundException {
        Vote vote = command.createEntity();
        User user = userRepository.findById(new UserId(command.getUserId())).orElseThrow(NotFoundException::new);
        Question question = questionRepository.findById(new QuestionId(command.getVotableId())).orElseThrow(NotFoundException::new);
        vote.setUser(user);
        vote.setVotable(question);
        return voteRepository.save(vote);
    }
}
