package ch.heigvd.amt.starkoverflow.model;

import lombok.Data;

@Data
public class Profile {
    private String username;
    public Profile(String username) {
        this.username = username;
    }
}
