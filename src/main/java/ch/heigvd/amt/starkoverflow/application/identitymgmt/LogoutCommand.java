package ch.heigvd.amt.starkoverflow.application.identitymgmt;

import lombok.Builder;

@Builder
public class LogoutCommand {
    String userId;
}
