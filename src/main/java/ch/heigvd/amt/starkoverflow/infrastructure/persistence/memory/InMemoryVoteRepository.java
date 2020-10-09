package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.domain.vote.IVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
@Named("InMemoryVoteRepository")
public class InMemoryVoteRepository extends InMemoryRepository<Vote,VoteId> implements IVoteRepository {

}