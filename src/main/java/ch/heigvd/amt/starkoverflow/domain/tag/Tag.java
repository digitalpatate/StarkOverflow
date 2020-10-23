package ch.heigvd.amt.starkoverflow.domain.tag;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import lombok.Data;

@Data
public class Tag implements IEntity {
    private TagId id;
    private String name;
    private String color;

    public Tag(String name, String color) {
        this.id = new TagId();
        this.name = name;
        this.color = color;
    }

    public Tag(TagId id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}

