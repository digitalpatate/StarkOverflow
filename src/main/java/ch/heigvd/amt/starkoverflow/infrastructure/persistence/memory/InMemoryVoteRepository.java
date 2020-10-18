package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("InMemoryVoteRepository")
public class InMemoryVoteRepository extends InMemoryRepository<Vote,VoteId> implements IVoteRepository {

    @Override
    public Vote userVoteOnAnswer(UserId viewer, AnswerId answerId) {
        return null;
    }

    @Override
    public long getNbVotesOfAnswer(AnswerId answerId) {
        return 0;
    }
}