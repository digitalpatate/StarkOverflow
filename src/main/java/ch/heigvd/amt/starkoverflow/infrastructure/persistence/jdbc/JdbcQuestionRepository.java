package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;


@ApplicationScoped
@Named("JdbcQuestionRepository")
@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcQuestionRepository implements IQuestionRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;


    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }

    @Override
    public Question save(Question entity) {
        PreparedStatement statement = null;
        try {
            statement = dataSource.getConnection().prepareStatement(
                    "INSERT INTO questions(id, title, content, author)" +
                            "VALUES(?,?,?,?)");

            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getContent());
            statement.setString(4, entity.getAuthor().asString());


            statement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void remove(QuestionId id) {

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        String query = "SELECT * FROM questions";
        Collection<Question> findedQuestion = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();


            while (res.next()){
                Question question = new Question(
                        new QuestionId(res.getString("id")),
                        res.getString("title"),
                        res.getString("content"),
                        res.getDate("creation_date"),
                        new UserId()
                );
                log.info(question.toString());
                findedQuestion.add(question);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findedQuestion;
    }
}
