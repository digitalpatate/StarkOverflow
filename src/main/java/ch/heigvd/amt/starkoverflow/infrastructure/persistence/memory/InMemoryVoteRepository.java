package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.domain.vote.IVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import java.util.Collection;
import java.util.Optional;

public class InMemoryVoteRepository implements IVoteRepository {
    @Override
    public void save(Vote entity) {

    }

    @Override
    public void remove(VoteId id) {

    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        return null;
    }
}
