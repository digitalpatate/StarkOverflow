package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionRepository")
//@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcQuestionRepository extends JdbcRepository implements IQuestionRepository {

    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }

    @Override
    public Question save(Question entity) {
        super.insert("questions",
                Arrays.asList(
                        "qa_id",
                        "title",
                        "content",
                        "author"
                ), Arrays.asList(
                        entity.getId().asString(),
                        entity.getTitle(),
                        entity.getContent(),
                        entity.getAuthor().asString()
                ));

        return entity;
    }

    @Override
    public void remove(QuestionId id) {
        super.remove("questions", "qa_id", id); // FIXME: maybe put those strings as var in superclass
    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        Optional<IEntity> question = super.find("questions", "qa_id", id.asString()); // FIXME: maybe put those strings as var in superclass

        return question.map(entity -> Optional.of((Question) entity)).orElse(null);
    }

    @Override
    public Collection<Question> findAll() {
        return (Collection) super.findAll("questions");
    }

    @Override
    public Question resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Question(
                new QuestionId(resultSet.getString("qa_id")),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getDate("creation_date"),
                new UserId(resultSet.getString("author"))
        );
    }
}
