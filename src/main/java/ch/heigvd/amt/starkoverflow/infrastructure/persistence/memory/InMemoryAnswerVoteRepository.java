package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("InMemoryAnswerVoteRepository")
public class InMemoryAnswerVoteRepository extends InMemoryRepository<Vote,VoteId> implements IAnswerVoteRepository {

    @Override
    public Vote userVoteOnAnswer(UserId viewer, AnswerId answerId) {
        return null;
    }

    @Override
    public long getNbVotesOfAnswer(AnswerId answerId) {
        return 0;
    }
}