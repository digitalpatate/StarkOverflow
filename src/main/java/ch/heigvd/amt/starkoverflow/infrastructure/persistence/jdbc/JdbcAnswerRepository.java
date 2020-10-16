package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerQuery;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerRepository")
//@NoArgsConstructor
@AllArgsConstructor
public class JdbcAnswerRepository extends JdbcRepository implements IAnswerRepository {

    @Override
    public Collection<Answer> find(AnswerQuery query) {
        return null;
    }

    @Override
    public Answer save(Answer entity) {
        super.insert("answers", Arrays.asList(
                "answer_id",
                "fk_author",
                "content",
                "fk_question"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getUserId().asString(),
                entity.getContent(),
                entity.getQuestionId().asString()
        ));

        return entity;
    }

    @Override
    public void remove(AnswerId id) {

    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Answer> findAll() {
        return null;
    }

    @Override
    public Answer resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Answer(
                new AnswerId(resultSet.getString("answer_id")),
                resultSet.getString("content"),
                resultSet.getDate("creation_date"),
                new UserId(resultSet.getString("fk_author")),
                new QuestionId(resultSet.getString("fk_question")),
                resultSet.getBoolean("approuval_state")
        );
    }
}
