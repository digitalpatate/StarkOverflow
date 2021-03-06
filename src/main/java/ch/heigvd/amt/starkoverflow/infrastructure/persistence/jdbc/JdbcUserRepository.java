package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.User;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcUserRepository")
//@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcUserRepository extends JdbcRepository implements IUserRepository {

    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }

    @Override
    public User save(User entity) {
        super.insert("users", Arrays.asList(
                "user_id",
                "email",
                "profile_picture_url",
                "firstname",
                "lastname",
                "username",
                "password"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getEmail(),
                entity.getProfilePictureURL(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getUsername(),
                entity.getEncryptedPassword()
        ));

        return entity;
    }

    @Override
    public void remove(UserId id) {
        super.remove("users", "user_id", id); // FIXME: maybe put those strings as var in superclass
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<IEntity> user = super.find("users", "email", email); // FIXME: maybe put those strings as var in superclass

        return user.map(entity -> (User) entity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<IEntity> user = super.find("users", "username", username); // FIXME: maybe put those strings as var in superclass

        return user.map(entity -> (User) entity);
    }

    @Override
    public int getTotalUser() {
        int nbUser = -1;
        try {
            ResultSet resultSet = safeExecuteQuery("SELECT COUNT(*) AS totalUser FROM users",null);
            if(resultSet.next()) {
                nbUser = resultSet.getInt("totalUser");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nbUser;
    }

    @Override
    public Optional<User> findById(UserId id) {
        Optional<IEntity> user = super.find("users", "user_id", id.asString()); // FIXME: maybe put those strings as var in superclass

        return user.map(entity -> (User) entity);
    }

    @Override
    public Collection<User> findAll() {
        return (Collection) super.findAll("users");
    }

    @Override
    public User resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new User(
                new UserId(resultSet.getString("user_id")),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("password"),
                resultSet.getString("profile_picture_url"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname")
        );
    }

}
