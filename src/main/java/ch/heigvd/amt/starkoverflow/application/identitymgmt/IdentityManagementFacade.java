package ch.heigvd.amt.starkoverflow.application.identitymgmt;

import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegistrationFailedException;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;


public class IdentityManagementFacade {
    private IUserRepository userRepository;

    public IdentityManagementFacade(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterCommand command) throws RegistrationFailedException {
        User existingUserWithSameUsername = userRepository.findByUsername(command.getUsername()).orElse(null);

        if(existingUserWithSameUsername != null) {
            throw new RegistrationFailedException("Username is already used!");
        }

        try{
            User newUser = User.builder()
                .username(command.getUsername())
                .email(command.email)
                .encryptedPassword(command.clearTextPassword)
                .firstname(command.firstname)
                .lastname(command.lastname)
                .profilePictureURL(command.profilePicture)
                .build();

            userRepository.save(newUser);
        } catch (Exception e) {
            throw new RegistrationFailedException(e.getMessage());
        }
    }

    public CurrentUserDTO authenticate(AuthenticateCommand command) throws AuthenticationFailedException {
        User user = userRepository.findByUsername(command.getUsername())
                .orElseThrow(() -> new AuthenticationFailedException("Verification of credentials failed!"));

        boolean success = user.authenticate(command.getClearTextPassword());
        if(!success){
            throw new AuthenticationFailedException("Verification of credentials failed!");
        }

        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .username(command.getUsername())
                .email(command.getEmail())
                .firstname(command.getFirstname())
                .lastname(command.getLastname())
                .profilePicture(command.getProfilePicture())
                .build();

        return currentUser;
    }
}
