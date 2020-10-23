package ch.heigvd.amt.starkoverflow.application.Answer.dto;

import ch.heigvd.amt.starkoverflow.application.Comment.dto.CommentsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnswerDTO {
    private String id;
    private String content;
    private UserDTO user;
    private CommentsDTO comments;
    private boolean voted;
    private long nbVotes;
}
