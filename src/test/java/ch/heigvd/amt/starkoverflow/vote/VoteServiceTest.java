package ch.heigvd.amt.starkoverflow.vote;

import ch.heigvd.amt.starkoverflow.application.Vote.CreateVoteCommand;
import ch.heigvd.amt.starkoverflow.application.Vote.VoteService;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IVoteRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
public class VoteServiceTest {

    private IVoteRepository voteRepository;
    private IUserRepository userRepository;
    private IQuestionRepository questionRepository;
    private IAnswerRepository answerRepository;
    private VoteService voteService;

    private CreateVoteCommand command;

    @BeforeEach
    private void setup(){
        this.voteRepository = mock(IVoteRepository.class);
        this.userRepository = mock(IUserRepository.class);
        this.questionRepository = mock(IQuestionRepository.class);
        this.answerRepository = mock(IAnswerRepository.class);
        this.voteService = new VoteService(voteRepository,userRepository,questionRepository,answerRepository);
        this.command = CreateVoteCommand.builder()
                .userId(new UserId().asString())
                .answerId(new AnswerId().asString())
                .build();

    }

   /* public void createAVoteShouldReturnAnObject(){

        when(voteRepository.save(null)).thenReturn(new Vote());
        Vote createdVote = this.voteService.createVote(command);

        assertEquals(createdVote.getUser().getId(),command.getUserId());
        assertEquals(createdVote.getVotable().getId(),command.getVotableId());

    }*/
}
