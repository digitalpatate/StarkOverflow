package ch.heigvd.amt.starkoverflow.application.User.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
public class UsersDTO {
    @Singular
    private List<UserDTO> users;
}
