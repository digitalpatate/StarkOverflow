package ch.heigvd.amt.starkoverflow.domain.user;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import javax.validation.constraints.*;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import lombok.*;
import org.apache.commons.validator.Arg;

import java.util.Date;

import static ch.heigvd.amt.starkoverflow.infrastructure.security.argon2.Parameters.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder(toBuilder = true)
public class User implements IEntity {


    private UserId id;
    private String email;
    private String profilePictureURL;
    private String firstname;
    private String lastname;
    private String username;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    private Date registrationDate;

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
        private Argon2 argon2;
        public UserBuilder hashPassword(String plainPassword){

            if(plainPassword == null || plainPassword.isEmpty()){
                throw new java.lang.IllegalArgumentException("mot de passe vide");
            }

            argon2 = Argon2Factory.create(
                    Argon2Factory.Argon2Types.ARGON2id,
                    SALT_LENGTH,
                    HASH_LENGTH);

            char[] password = plainPassword.toCharArray();
            try {

                encryptedPassword = argon2.hash(ITERATIONS, MEMORY, PARALLELISM, password);

            } finally {
                argon2.wipeArray(password);
            }
            return this;
        }

        public boolean verifyPassword(String plainPassword){
            argon2 = Argon2Factory.create(
                    Argon2Factory.Argon2Types.ARGON2id,
                    SALT_LENGTH,
                    HASH_LENGTH);
            return argon2.verify(encryptedPassword, plainPassword.toCharArray());
        }

        public User build(){
            if(id==null){
                id = new UserId();
            }
            hashPassword(encryptedPassword);
            User newUser = new User(id,username ,email, encryptedPassword, profilePictureURL, firstname, lastname);
            return newUser;
        }
    }
}
