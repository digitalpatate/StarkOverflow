package ch.heigvd.amt.starkoverflow.application.Comment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentDTO {
    private String id;
    private String content;
    private Date creationDate;
    //Author DTO ?
    private String authorId;

}
