package ch.heigvd.amt.starkoverflow.application.Event;

import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.event.Event;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.time.OffsetDateTime;

@Builder
@Data
public class CreateEventCommand {
    @JsonbProperty("userUUID")
    private String userId;

    private String timestamp;
    private String type;

    public Event createEntity() {
        return new Event(new UserId(userId), OffsetDateTime.parse(timestamp), type);
    }
}
