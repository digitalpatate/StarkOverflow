package ch.heigvd.amt.starkoverflow.application.Tag.dto;

import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagDTO {
    private TagId id;
    private String name;
    private String color;

    public Tag createEntity() {
        return new Tag(id, name, color);
    }

}
