package ch.heigvd.amt.starkoverflow.application.identitymgmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    public String username;
    public String email;
    public String clearTextPassword;
    public String profilePicture;
    public String firstname;
    public String lastname;

    public static class RegisterCommandBuilder {
        private final static List<String> imageExts = Arrays.asList(
                "jpg",
                "jpeg",
                "gif",
                "png",
                "bmp",
                "svg"
        );

        public RegisterCommandBuilder profilePicture(String profilePicture) {
            if(urlIsImageURL(profilePicture)) {
                this.profilePicture = removeHTTPFromImageURL(profilePicture);
            } else {
                throw new IllegalArgumentException("Profile picture url must be an image!");
            }
            return this;
        }

        private String removeHTTPFromImageURL(String url) {
            final String http = "http:";

            if(url.contains(http)) {
                url = url.substring(http.length());
            }

            return url;
        }

        private boolean urlIsImageURL(String url) {
            String[] urlParts = url.split("\\.");
            String urlExt = urlParts[urlParts.length - 1].split("\\?")[0].toLowerCase();

            return imageExts.contains(urlExt);
        }
    }
}
