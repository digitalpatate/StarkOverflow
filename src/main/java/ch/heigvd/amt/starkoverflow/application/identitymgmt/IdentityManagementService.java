package ch.heigvd.amt.starkoverflow.application.identitymgmt;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegistrationFailedException;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;

@NoArgsConstructor
@AllArgsConstructor
@Named("IdentityManagementService")
@ApplicationScoped
public class IdentityManagementService {

    @Inject @Named("JdbcUserRepository")
    private IUserRepository userRepository;



    public void register(RegisterCommand command) throws RegistrationFailedException {
        User existingUserWithSameEmail = userRepository.findByEmail(command.getEmail()).orElse(null);

        if(existingUserWithSameEmail != null) {
            throw new RegistrationFailedException("Username is already used!");
        }

        //try{
            User newUser = User.builder()
                .username(command.getUsername())
                .email(command.email)
                .encryptedPassword(command.clearTextPassword)
                .firstname(command.firstname)
                .lastname(command.lastname)
                .profilePictureURL(command.profilePicture)
                .build();

            userRepository.save(newUser);
        /*} catch (Exception e) {
            System.out.println("FAIL : save failed");
            throw new RegistrationFailedException(e.getMessage());
        }*/
    }

    public UserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
        User user = userRepository.findByEmail(command.getEmail())
                .orElseThrow(() -> new NotFoundException("Cannot find user with this email"));

        boolean success = user.authenticate(command.getClearTextPassword());
        if(!success){
            throw new AuthenticationFailedException("Verification of credentials failed!");
        }

        UserDTO currentUser = UserDTO.builder()
                .id(user.getId().asString())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .profilePicture(user.getProfilePictureURL())
                .build();

        return currentUser;
    }

}
