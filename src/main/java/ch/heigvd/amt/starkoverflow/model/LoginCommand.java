package ch.heigvd.amt.starkoverflow.model;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class LoginCommand {
    public String email;
    public String password;
}
