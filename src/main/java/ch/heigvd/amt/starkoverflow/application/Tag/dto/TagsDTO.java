package ch.heigvd.amt.starkoverflow.application.Tag.dto;

import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class TagsDTO {
    @Singular
    private List<TagDTO> tags;
 }
