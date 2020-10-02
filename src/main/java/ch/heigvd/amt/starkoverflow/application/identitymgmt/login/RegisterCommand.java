package ch.heigvd.amt.starkoverflow.application.identitymgmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    public String username;
    public String email;
    public String clearTextPassword;
    public String profilePicture;
    public String firstname;
    public String lastname;
}
