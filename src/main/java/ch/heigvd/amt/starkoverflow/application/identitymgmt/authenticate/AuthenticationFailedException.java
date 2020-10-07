package ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate;

import lombok.Value;

public class AuthenticationFailedException extends Exception {

    public AuthenticationFailedException(String message) {
        super(message);
    }
}
