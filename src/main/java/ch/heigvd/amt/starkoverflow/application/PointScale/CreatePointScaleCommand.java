package ch.heigvd.amt.starkoverflow.application.PointScale;

import ch.heigvd.amt.starkoverflow.application.Rule.ActionDTO;
import ch.heigvd.amt.starkoverflow.application.Rule.ConditionDTO;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Builder
@Data
public class CreatePointScaleCommand {
    private String description;
    private String name;
}
