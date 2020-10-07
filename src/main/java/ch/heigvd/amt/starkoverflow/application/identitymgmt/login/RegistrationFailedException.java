package ch.heigvd.amt.starkoverflow.application.identitymgmt.login;

import lombok.Value;

public class RegistrationFailedException extends Exception {

    public RegistrationFailedException(String message) {
        super(message);
    }
}
