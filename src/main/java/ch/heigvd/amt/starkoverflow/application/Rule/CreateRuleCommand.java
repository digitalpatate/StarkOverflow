package ch.heigvd.amt.starkoverflow.application.Rule;

import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Builder
@Data
public class CreateRuleCommand {
    @JsonbProperty("condition")
    private ConditionDTO conditionDTO;

    @JsonbProperty("then")
    private ActionDTO actionDTO;
}
