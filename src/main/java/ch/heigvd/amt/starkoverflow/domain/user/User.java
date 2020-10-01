package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import javax.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class User implements IEntity {


    private UserId id;

    @NotNull
    private String email;
    private String password;
    private String profilePictureURL;
    private String firstname;
    private String lastname;

    private Date registrationDate;



    public User(String email, String password, String profilePictureURL, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.profilePictureURL = profilePictureURL;
        this.firstname = firstname;
        this.lastname = lastname;

        this.id = new UserId();
        this.registrationDate = new Date();
    }
}
