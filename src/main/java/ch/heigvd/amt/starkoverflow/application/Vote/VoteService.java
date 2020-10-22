package ch.heigvd.amt.starkoverflow.application.Vote;

import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
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

    @Inject @Named("JdbcAnswerVoteRepository")
    private IAnswerVoteRepository answerVoteRepository;
    @Inject @Named("JdbcQuestionVoteRepository")
    private IQuestionVoteRepository questionVoteRepository;
    @Inject @Named("JdbcAnswerRepository")
    private IAnswerRepository answerRepository;
    @Inject @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;

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

    public Vote createQuestionVote(CreateQuestionVoteCommand command) {
        Vote vote = command.createEntity();
        UserId userId = new UserId(command.getUserId());
        QuestionId questionId = new QuestionId(command.getQuestionId());

        Vote userVote = questionVoteRepository.userVoteOnQuestion(userId, questionId);
        if(userVote != null) {
            // delete his vote
            questionVoteRepository.remove(userVote.getId());
            return null;
        } else if (questionRepository.findById(questionId).map(question -> question.getAuthor() == userId).orElse(false)) {
            throw new IllegalArgumentException("User can't vote on his own question");
        } else {
            // insert his vote
            return questionVoteRepository.save(vote);
        }
    }
}
