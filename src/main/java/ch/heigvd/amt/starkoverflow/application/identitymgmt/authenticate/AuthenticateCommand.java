package ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AuthenticateCommand {
    private String username;
    private String email;
    private String clearTextPassword;
    private String profilePicture;
    private String firstname;
    private String lastname;
}
