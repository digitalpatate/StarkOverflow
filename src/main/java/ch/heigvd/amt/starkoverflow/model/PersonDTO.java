package ch.heigvd.amt.starkoverflow.model;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class PersonDTO {
    public String email;
    public String password;
}
