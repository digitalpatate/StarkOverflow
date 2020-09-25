package ch.heigvd.amt.starkoverflow.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Statistic {
    String title;
    String data;
}
