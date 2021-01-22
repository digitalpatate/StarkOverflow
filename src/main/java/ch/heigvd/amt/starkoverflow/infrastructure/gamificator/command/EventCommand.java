package ch.heigvd.amt.starkoverflow.infrastructure.gamificator.command;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;
@Getter
public class EventCommand {
    private UUID userUUID;
    private OffsetDateTime timestamp;
    private String type;

    public EventCommand(UUID userUUID, OffsetDateTime timestamp, String type) {
        this.userUUID = userUUID;
        this.timestamp = timestamp;
        this.type = type;
    }
}
