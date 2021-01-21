package ch.heigvd.amt.starkoverflow.application.Rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Data
@AllArgsConstructor
public class ActionDTO {

    @JsonbProperty("awardBadges")
    private List<String> awardBadgeDTOList;

    @JsonbProperty("awardPoints")
    private List<AwardPointDTO> awardPointDTOList;
}
