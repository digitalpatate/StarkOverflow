package ch.heigvd.amt.starkoverflow.application.Tag;

import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import lombok.Builder;
import lombok.Getter;

import java.util.Random;

@Builder(toBuilder = true)
@Getter
public class CreateTagCommand {

    private String name;
    private String color;

    public Tag createEntity() {
        return new Tag(name, randomColorHex());
    }

    private String randomColorHex() {
        Random rand = new Random();
        int randHex = rand.nextInt(0xffffff + 1);
        return String.format("#%06x", randHex);
    }
}

