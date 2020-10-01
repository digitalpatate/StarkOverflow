package ch.heigvd.amt.starkoverflow.application.Tag.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDTO {
    private String name;
    private String color;
}
