package ch.heigvd.amt.starkoverflow.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginCommand {
    public String email;
    public String password;
}
