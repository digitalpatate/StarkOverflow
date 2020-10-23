package ch.heigvd.amt.starkoverflow.application.Comment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class CommentsDTO {
    @Singular
    private List<CommentDTO> comments;
}
