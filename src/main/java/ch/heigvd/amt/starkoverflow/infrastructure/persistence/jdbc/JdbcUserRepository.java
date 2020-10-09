package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
import ch.heigvd.amt.starkoverflow.domain.user.IUserRepository;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcUserRepository")
@NoArgsConstructor
@AllArgsConstructor
public class JdbcUserRepository implements IUserRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;


    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @SneakyThrows
    @Override
    public User save(User entity) {
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO users(uuid, firstname, lastname, email, password)" +
                            "VALUES(?,?,?,?,?)");
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getFirstname());
            statement.setString(3, entity.getLastname());
            statement.setString(4, entity.getEmail());
            statement.setString(5, entity.getEncryptedPassword());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;

    }

    @Override
    public void remove(UserId id) {

    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.empty();
    }

    @Override
    public Collection<User> findAll() {
        return null;
    }
}
