package ch.heigvd.amt.starkoverflow.application.User;


import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
@Getter
public class CreateUserCommand {

    private String email;
    private String imageUrl;
    private String firstname;
    private String lastname;
    private String password;
    private String regDate;

    /*public User createEntity() {
        return new User(email,imageUrl,firstname,lastname,password,regDate);
    }*/
}
