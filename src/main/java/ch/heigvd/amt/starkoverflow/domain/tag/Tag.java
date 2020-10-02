package ch.heigvd.amt.starkoverflow.domain.tag;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Votable;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;
import lombok.Builder;
import lombok.Data;

@Data
public class Tag implements IEntity<Tag, TagId> {
    private TagId id;
    private String name;
    // HEX code ?
    private String color;

    public Tag(String name, String color) {
        this.id = new TagId();
        this.name = name;
        this.color = color;
        this.id = new TagId();
    }


    //TODO complèter la fonction
    @Override
    public Tag deepClone(){
        return null;
    }
}
