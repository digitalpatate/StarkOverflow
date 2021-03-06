package ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
public class AuthenticateCommand {
    private String email;
    private String clearTextPassword;
}
