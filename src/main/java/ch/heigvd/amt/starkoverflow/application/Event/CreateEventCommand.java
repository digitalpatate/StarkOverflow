package ch.heigvd.amt.starkoverflow.application.Event;

import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Builder
@Data
public class CreateEventCommand {
    @JsonbProperty("userUUID")
    private String userId;

    private String timestamp;
    private String type;
}
