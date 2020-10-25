package ch.heigvd.amt.starkoverflow.application.User;

import ch.heigvd.amt.starkoverflow.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
public class CreateUserCommand {

    private String email;
    private String imageUrl;
    private String firstname;
    private String lastname;
    private String password;
    private Date regDate;

    public User createEntity() {
        return User.builder()
                .email(email)
                .profilePictureURL(imageUrl)
                .firstname(firstname)
                .lastname(lastname)
                .encryptedPassword(password)
                .registrationDate(regDate)
                .build();
    }
}
