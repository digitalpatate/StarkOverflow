package ch.heigvd.amt.starkoverflow.application.Rule;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AwardPointDTO {
    private String pointScaleName;
    private Integer value;
}
