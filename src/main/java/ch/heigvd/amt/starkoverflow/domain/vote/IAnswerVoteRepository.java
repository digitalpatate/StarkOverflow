package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import java.util.Collection;

public interface IAnswerVoteRepository extends IRepository<Vote, VoteId> {
    Vote userVoteOnAnswer(UserId viewer, AnswerId answerId);
    long getNbVotesOfAnswer(AnswerId answerId);

    int getTotalAnswerVoteRepository();
}
