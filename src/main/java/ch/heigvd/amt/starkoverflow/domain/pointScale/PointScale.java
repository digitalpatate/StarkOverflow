package ch.heigvd.amt.starkoverflow.domain.pointScale;

import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class PointScale {
    private String name;
    private String description;

}
