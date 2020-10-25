package ch.heigvd.amt.starkoverflow.user;

import ch.heigvd.amt.starkoverflow.domain.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    private User alice;
    private User.UserBuilder userBuilder;

    private final String
            username = "Alice",
            email = "alice@wonderland.com",
            PWD = "pursuer_of_rabbit",
            profilePictureUrl = "http://en.wikipedia.org",
            firstname = "Alice",
            lastname = "Wonderland",
            EMPTY = "";


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
    void passwordCannotBeNullOrEmpty(){
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.hashPassword(EMPTY));
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.hashPassword(null));
    }

    @Test
    void UsernameCannotBeNullOrEmpty(){
        userBuilder.username(null);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
        userBuilder.username(EMPTY);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
    }

    @Test
    void emailCannotBeNullOrEmpty(){
        userBuilder.email(null);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
        userBuilder.email(EMPTY);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
    }

    @Test
    void profilePictureURLCannotBeNullOrEmpty(){
        userBuilder.profilePictureURL(null);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
        userBuilder.profilePictureURL(EMPTY);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
    }

    @Test
    void firstnameCannotBeNullOrEmpty(){
        userBuilder.firstname(null);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
        userBuilder.firstname(EMPTY);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
    }

    @Test
    void lastnameCannotBeNullOrEmpty(){
        userBuilder.lastname(null);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
        userBuilder.lastname(EMPTY);
        assertThrows(
                IllegalArgumentException.class,
                () ->userBuilder.build());
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
