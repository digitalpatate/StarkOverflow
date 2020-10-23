package ch.heigvd.amt.starkoverflow.application.Comment.dto;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentDTO {
    private String id;
    private String content;
    private Date creationDate;
    private UserDTO user;
}
