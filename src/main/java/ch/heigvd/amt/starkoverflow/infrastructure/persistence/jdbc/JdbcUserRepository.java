package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.User.UserQuery;
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
@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcUserRepository implements IUserRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;


    @Override
    public Collection<User> find(UserQuery query) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = String.format("SELECT * FROM users WHERE email='%s'", email);
        PreparedStatement statement = null;
        try {
            statement = dataSource.getConnection().prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collection<User> foundedUser = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery();
            while (res.next()) {
                User user = User.builder()
                        .email(res.getString("email"))
                        .id(new UserId(res.getString("user_id")))
                        .username(res.getString("username"))
                        .profilePictureURL(res.getString("profile_picture_url"))
                        .firstname(res.getString("firstname"))
                        .lastname(res.getString("lastname"))
                        .encryptedPassword(res.getString("password"))
                        .build();

                foundedUser.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return foundedUser.stream().findFirst();

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
        String query = String.format("DELETE FROM users WHERE user_id = '%s';", id.asString());
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(UserId id) {
        String  query = String.format("SELECT * FROM users WHERE qa_id='%s'",id.asString());

        PreparedStatement statement = null;
        try {
            statement = dataSource.getConnection().prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collection<User> foundedUser = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery();
            while (res.next()){
                User user = resultSetToEntity(res);
                foundedUser.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foundedUser.stream().findFirst();
    }

    @Override
    public Collection<User> findAll() {
        String query = String.format("SELECT * FROM users");
        Collection<User> foundedUsers = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();


            /*
             * Not sure if it's working ...
             */
            while (res.next()){
                User user = resultSetToEntity(res);
                log.info(user.toString());
                foundedUsers.add(user);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundedUsers;
    }


    private User resultSetToEntity(ResultSet resultSet) throws SQLException {
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
