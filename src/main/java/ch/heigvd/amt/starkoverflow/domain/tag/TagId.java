package ch.heigvd.amt.starkoverflow.domain.tag;

import ch.heigvd.amt.starkoverflow.domain.Id;

import java.util.UUID;

public class TagId extends Id {
    public TagId() {
        super();
    }

    public TagId(String id) {
        super(id);
    }

    public TagId(UUID id) {
        super(id);
    }

    @Override
    public String asString() {
        return super.asString();
    }
}
