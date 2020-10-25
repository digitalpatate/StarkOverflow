package ch.heigvd.amt.starkoverflow.infrastructure.memory.vote;

import ch.heigvd.amt.starkoverflow.application.Vote.CreateAnswerVoteCommand;
import ch.heigvd.amt.starkoverflow.application.Vote.VoteService;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;
public class VoteServiceTest {

    private IAnswerVoteRepository answerVoteRepository;
    private IQuestionVoteRepository questionVoteRepository;
    private IUserRepository userRepository;
    private IQuestionRepository questionRepository;
    private IAnswerRepository answerRepository;
    private VoteService voteService;

    private CreateAnswerVoteCommand command;

    @BeforeEach
    private void setup(){
        this.answerVoteRepository = mock(IAnswerVoteRepository.class);
        this.userRepository = mock(IUserRepository.class);
        this.questionRepository = mock(IQuestionRepository.class);
        this.answerRepository = mock(IAnswerRepository.class);
        this.voteService = new VoteService(answerVoteRepository,questionVoteRepository, answerRepository, questionRepository);
        this.command = CreateAnswerVoteCommand.builder()
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
