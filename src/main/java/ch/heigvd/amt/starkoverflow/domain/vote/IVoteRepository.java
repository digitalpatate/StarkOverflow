package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    Vote userVoteOnAnswer(UserId viewer, AnswerId answerId);
    long getNbVotesOfAnswer(AnswerId answerId);
}
