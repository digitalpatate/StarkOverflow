package ch.heigvd.amt.starkoverflow.user;

import ch.heigvd.amt.starkoverflow.domain.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class UserTest {
    private User alice;
    private User.UserBuilder userBuilder;

    private final String
            username = "Alice",
            email = "alice@wonderland.com",
            PWD = "pursuer_of_rabbit",
            profilePictureUrl = "http://en.wikipedia.org",
            firstname = "Alice",
            lastname = "Wonderland";


    @BeforeEach
    public void setup(){
        this.alice = User.builder()
                .username(username)
                .email(email)
                .encryptedPassword(PWD)
                .firstname(firstname)
                .lastname(lastname)
                .profilePictureURL(profilePictureUrl)
                .build();
        this.userBuilder = alice.toBuilder();
    }

    @Test
    void aliceShouldBeAbleToLoginWithARightPassword(){
       assertTrue(userBuilder.verifyPassword(PWD));
    }

    @Test
    void aliceShouldNotBeAbleToLoginWithAWrongPassword(){
        assertFalse(userBuilder.verifyPassword("pursuer_of_horses"));
    }

}
