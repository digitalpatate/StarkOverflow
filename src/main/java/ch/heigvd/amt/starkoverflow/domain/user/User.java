package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import javax.validation.constraints.*;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity {


    private UserId id;

    @NotNull
    private String email;
    private String profilePictureURL;
    private String firstname;
    private String lastname;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    private Date registrationDate;



    /*public User(String email, String password, String profilePictureURL, String firstname, String lastname) {
        this.email = email;
        this.encryptedPassword = password;
        this.profilePictureURL = profilePictureURL;
        this.firstname = firstname;
        this.lastname = lastname;

        this.id = new UserId();
        this.registrationDate = new Date();
    }*/


    public static class PersonBuilder {
        public PersonBuilder plainPassword(String plainPassword){

            if(plainPassword == null || plainPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("mot de passe vide");
            }

            //TODO Replacer toUpperCase avec une vrai fonction de chiffrement
            encryptedPassword = plainPassword.toUpperCase();
            return this;
        }

        public User build(){
            User newUser = new User(id, email, firstname, lastname, profilePictureURL, encryptedPassword);
            return newUser;
        }
    }
}
