package ch.heigvd.amt.starkoverflow.infrastructure.memory.vote;

import ch.heigvd.amt.starkoverflow.application.Vote.CreateAnswerVoteCommand;
import ch.heigvd.amt.starkoverflow.application.Vote.CreateQuestionVoteCommand;
import ch.heigvd.amt.starkoverflow.application.Vote.VoteService;
import ch.heigvd.amt.starkoverflow.domain.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
public class VoteServiceTest {

    private IAnswerVoteRepository answerVoteRepository;
    private IQuestionVoteRepository questionVoteRepository;
    private IQuestionRepository questionRepository;
    private IAnswerRepository answerRepository;
    private VoteService voteService;

    private CreateAnswerVoteCommand answerVoteCommand;
    private CreateQuestionVoteCommand questionVoteCommand;

    private UserId uId;
    private AnswerId aId;
    private QuestionId qId;
    private Vote vote;

    @BeforeEach
    private void setup(){
        this.answerVoteRepository = mock(IAnswerVoteRepository.class);
        this.questionVoteRepository = mock(IQuestionVoteRepository.class);
        this.questionRepository = mock(IQuestionRepository.class);
        this.answerRepository = mock(IAnswerRepository.class);
        this.voteService = new VoteService(answerVoteRepository, questionVoteRepository, answerRepository, questionRepository);

        uId = new UserId();
        aId = new AnswerId();
        qId = new QuestionId();
        vote = new Vote();

        this.answerVoteCommand = CreateAnswerVoteCommand.builder()
                .userId(uId.asString())
                .answerId(aId.asString())
                .build();

        this.questionVoteCommand = CreateQuestionVoteCommand.builder()
                .userId(uId.asString())
                .questionId(qId.asString())
                .build();
    }

    @Test
    public void aUserThatVoteAgainOnAnAnswerDeleteHisVote() {
        when(answerVoteRepository.userVoteOnAnswer(uId, aId)).thenReturn(vote);
        Assertions.assertEquals(null, voteService.createAnswerVote(answerVoteCommand));
    }

    @Test
    public void aUserCanNotVoteForHisOwnAnswer() {
        Answer ans = new Answer(aId, "", uId, new QuestionId());
        when(answerVoteRepository.userVoteOnAnswer(any(UserId.class), any(AnswerId.class))).thenReturn(null);
        when(answerRepository.findById(any(AnswerId.class))).thenReturn(Optional.of(ans));
        Assertions.assertThrows(IllegalArgumentException.class, () -> { voteService.createAnswerVote(answerVoteCommand); });
    }

    @Test
    public void aUserCanVoteOnAnAnswer() {
        when(answerVoteRepository.userVoteOnAnswer(any(UserId.class), any(AnswerId.class))).thenReturn(null);
        when(answerRepository.findById(any(AnswerId.class))).thenReturn(Optional.empty());
        when(answerVoteRepository.save(any(Vote.class))).thenReturn(vote);
        Assertions.assertEquals(vote, voteService.createAnswerVote(answerVoteCommand));
    }

    @Test
    public void aUserThatVoteAgainOnAQuestionDeleteHisVote() {
        when(questionVoteRepository.userVoteOnQuestion(uId, qId)).thenReturn(vote);
        Assertions.assertEquals(null, voteService.createQuestionVote(questionVoteCommand));
    }

    @Test
    public void aUserCanNotVoteForHisOwnQuestion() {
        Question ans = new Question(qId, "", "", new Date(), uId);
        when(questionVoteRepository.userVoteOnQuestion(any(UserId.class), any(QuestionId.class))).thenReturn(null);
        when(questionRepository.findById(any(QuestionId.class))).thenReturn(Optional.of(ans));
        Assertions.assertThrows(IllegalArgumentException.class, () -> { voteService.createQuestionVote(questionVoteCommand); });
    }

    @Test
    public void aUserCanVoteOnAQuestion() {
        when(questionVoteRepository.userVoteOnQuestion(any(UserId.class), any(QuestionId.class))).thenReturn(null);
        when(questionRepository.findById(any(QuestionId.class))).thenReturn(Optional.empty());
        when(questionVoteRepository.save(any(Vote.class))).thenReturn(vote);
        Assertions.assertEquals(vote, voteService.createQuestionVote(questionVoteCommand));
    }
}
