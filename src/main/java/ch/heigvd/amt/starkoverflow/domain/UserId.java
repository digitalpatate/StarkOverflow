package ch.heigvd.amt.starkoverflow.domain;

import ch.heigvd.amt.starkoverflow.domain.Id;

import java.util.UUID;

public class UserId extends Id {

    public UserId() {
        super();
    }

    public UserId(String id) {
        super(id);
    }

    public UserId(UUID id) {
        super(id);
    }

    @Override
    public String asString() {
        return super.asString();
    }
}
