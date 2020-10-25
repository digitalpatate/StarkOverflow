package ch.heigvd.amt.starkoverflow.application.User;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UsersDTO;
import ch.heigvd.amt.starkoverflow.domain.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.User;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@NoArgsConstructor
@AllArgsConstructor
@ApplicationScoped
@Named("UserService")
public class UserService {

    @Inject @Named("JdbcUserRepository")
    private IUserRepository userRepository;


    public UserDTO getUser(String id) {
        User user = userRepository.findById(
                new UserId(id)).orElseThrow(() -> new RuntimeException("User not found")
        );

        return UserDTO.builder()
                .id(user.getId().asString())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    public UsersDTO getAllUsers() {
        Collection<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = users.stream()
                .map(user -> UserDTO.builder()
                        .id(user.getId().asString())
                        .email(user.getEmail())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .profilePicture(user.getProfilePictureURL())
                        .username(user.getUsername())
                        .build())
                .collect(Collectors.toList());

        return UsersDTO.builder().users(usersDTO).build();

    }

    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User with "+username+" not found !"));

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId().asString())
                .email(user.getEmail())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .profilePicture(user.getProfilePictureURL())
                .username(user.getUsername())
                .build();

        return userDTO;

    }
}
