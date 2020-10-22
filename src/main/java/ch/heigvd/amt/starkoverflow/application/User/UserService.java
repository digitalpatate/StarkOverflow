package ch.heigvd.amt.starkoverflow.application.User;

import ch.heigvd.amt.starkoverflow.application.Tag.CreateTagCommand;
import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UsersDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.ejb.ApplicationException;
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
