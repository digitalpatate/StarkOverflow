package ch.heigvd.amt.starkoverflow.domain.event;

import ch.heigvd.amt.starkoverflow.domain.Id;

import java.util.UUID;

public class EventId extends Id {
    public EventId() {
        super();
    }

    public EventId(String id) {
        super(id);
    }

    public EventId(UUID id) {
        super(id);
    }
}
