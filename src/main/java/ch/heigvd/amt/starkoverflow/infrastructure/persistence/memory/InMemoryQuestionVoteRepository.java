package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("InMemoryQuestionVoteRepository")
public class InMemoryQuestionVoteRepository extends InMemoryRepository<Vote,VoteId> implements IQuestionVoteRepository {

    @Override
    public Vote userVoteOnQuestion(UserId viewer, QuestionId questionId) {
        return null;
    }

    @Override
    public long getNbVotesOfQuestion(QuestionId questionId) {
        return 0;
    }

    @Override
    public int getTotalQuestionVoteRepository() {
        return 0;
    }
}