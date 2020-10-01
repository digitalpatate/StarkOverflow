package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
}
