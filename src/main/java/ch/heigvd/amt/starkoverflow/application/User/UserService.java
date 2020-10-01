package ch.heigvd.amt.starkoverflow.application.User;

import ch.heigvd.amt.starkoverflow.application.Tag.CreateTagCommand;
import ch.heigvd.amt.starkoverflow.application.Tag.TagQuery;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagDTO;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UsersDTO;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createTag(CreateUserCommand command){
        User user = command.createEntity();
        userRepository.save(user);
    }

    public UsersDTO getTag(UserQuery query){
        Collection<User> users = userRepository.find(query);

        List<UserDTO> userDTO = users.stream().map(user -> UserDTO.builder().build()).collect(Collectors.toList());

        return UsersDTO.builder().users(userDTO).build();
    }
}
