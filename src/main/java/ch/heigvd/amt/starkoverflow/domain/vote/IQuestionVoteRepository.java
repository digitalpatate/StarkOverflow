package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

public interface IQuestionVoteRepository extends IRepository<Vote, VoteId> {
    Vote userVoteOnQuestion(UserId viewer, QuestionId questionId);
    long getNbVotesOfQuestion(QuestionId questionId);
}
