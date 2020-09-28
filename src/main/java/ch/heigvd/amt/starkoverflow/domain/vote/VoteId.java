package ch.heigvd.amt.starkoverflow.domain.vote;

import ch.heigvd.amt.starkoverflow.domain.Id;

import java.util.UUID;

public class VoteId extends Id {
    public VoteId() {
        super();
    }

    public VoteId(String id) {
        super(id);
    }

    public VoteId(UUID id) {
        super(id);
    }

    @Override
    public String asString() {
        return super.asString();
    }
}
