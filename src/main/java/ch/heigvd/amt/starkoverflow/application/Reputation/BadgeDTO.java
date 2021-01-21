package ch.heigvd.amt.starkoverflow.application.Reputation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@NoArgsConstructor
public class BadgeDTO {

    private Long id;

    private String name;

    private int occurence;

    private URI imageUrl;
}
