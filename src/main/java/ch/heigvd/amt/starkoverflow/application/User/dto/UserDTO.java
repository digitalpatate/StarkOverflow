package ch.heigvd.amt.starkoverflow.application.User.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
    private String id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String profilePicture;
}
