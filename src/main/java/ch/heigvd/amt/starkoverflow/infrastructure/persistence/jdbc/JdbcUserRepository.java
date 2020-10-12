package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    @SneakyThrows
    @Override
    public User save(User entity) {
        String query = String.format("INSERT INTO users(user_id, email, profile_picture_url, firstname, lastname, username, password)" +
                "VALUES(?,?,?,?,?,?, ?)");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            System.out.println();
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getProfilePictureURL());
            statement.setString(4, entity.getFirstname());
            statement.setString(5, entity.getLastname());
            statement.setString(6, entity.getUsername());
            statement.setString(7, entity.getEncryptedPassword());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void remove(UserId id) {
        super.remove("users", "user_id", id); // FIXME: maybe put those strings as var in superclass
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<IEntity> user = super.find("users", "email", email); // FIXME: maybe put those strings as var in superclass

        return user.map(entity -> Optional.of((User) entity)).orElse(null);
    }

    @Override
    public Optional<User> findById(UserId id) {
        Optional<IEntity> user = super.find("users", "user_id", id.asString()); // FIXME: maybe put those strings as var in superclass

        return user.map(entity -> Optional.of((User) entity)).orElse(null);
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
