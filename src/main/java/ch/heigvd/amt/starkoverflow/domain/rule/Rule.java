package ch.heigvd.amt.starkoverflow.domain.rule;

import ch.heigvd.amt.starkoverflow.application.Rule.AwardPointDTO;
import lombok.Data;
import java.util.List;

@Data
public class Rule {

    List<String> awardBadges;
    List<AwardPointDTO> awardPointDTOList;
    String conditionType;

    public Rule(List<String> awardBadges, List<AwardPointDTO> awardPointDTOList, String conditionType) {
        this.awardBadges = awardBadges;
        this.awardPointDTOList = awardPointDTOList;
        this.conditionType = conditionType;
    }
}
