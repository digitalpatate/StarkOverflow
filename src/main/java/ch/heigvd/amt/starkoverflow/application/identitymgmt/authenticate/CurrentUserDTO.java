package ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
public class CurrentUserDTO {
    public String username;
    public String email;
    public String profilePicture;
    public String firstname;
    public String lastname;
}
