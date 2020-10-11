package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import javax.validation.constraints.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {


    private UserId id;

    private String email;
    private String profilePictureURL;
    private String firstname;
    private String lastname;
    private String username;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    private Date registrationDate;

    //TODO Replacer toUpperCase avec une vrai fonction de chiffrement
    public boolean authenticate(String plainPassword){
        return plainPassword.equals(encryptedPassword);
    }

    @Override
    public User deepClone(){
        return this.toBuilder().id(new UserId(id.asString())).build();
    }

    public User(UserId id,String username, String email, String password, String profilePictureURL, String firstname, String lastname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.encryptedPassword = password;
        this.profilePictureURL = profilePictureURL;
        this.firstname = firstname;
        this.lastname = lastname;

        this.registrationDate = new Date();
    }


    public static class UserBuilder {
        public UserBuilder plainPassword(String plainPassword){

            if(plainPassword == null || plainPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("mot de passe vide");
            }

            //TODO Replacer toUpperCase avec une vrai fonction de chiffrement
            encryptedPassword = plainPassword.toUpperCase();
            return this;
        }

        public User build(){
            if(id==null){
                id = new UserId();
            }
            User newUser = new User(id,username ,email, encryptedPassword, profilePictureURL, firstname, lastname);
            return newUser;
        }
    }
}
