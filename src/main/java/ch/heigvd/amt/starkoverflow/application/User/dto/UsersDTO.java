package ch.heigvd.amt.starkoverflow.application.User.dto;

import lombok.Builder;
import lombok.Singular;

import java.util.List;

@Builder
public class UsersDTO {
    @Singular
    private List<UserDTO> users;
}
