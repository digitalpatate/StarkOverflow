package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;


@ApplicationScoped
@Named("JdbcQuestionRepository")
@NoArgsConstructor
@AllArgsConstructor
public class JdbcQuestionRepository implements IQuestionRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;


    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }

    @Override
    public Question save(Question entity) {
        return null;
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
        return null;
    }
}
