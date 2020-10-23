package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc.utils.QueryBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerRepository")
@NoArgsConstructor
public class JdbcAnswerRepository extends JdbcRepository implements IAnswerRepository {
    @Inject
    @Named("JdbcQuestionRepository")
    private JdbcQuestionRepository questionRepository;

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
    public Optional<Question> getAnswerQuestion(AnswerId id) {
        Optional<Answer> answer = findById(id);

        if(answer.isPresent()) {
            return questionRepository.findById(answer.get().getQuestionId());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void accept(AnswerId answerId) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("UPDATE answers SET approuval_state=true WHERE answer_id=?");
            preparedStatement.setString(1, answerId.asString());
            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        Optional<IEntity> answer =  super.find("answers", "answer_id", id.asString());

        return answer.map(entity -> (Answer) entity);


    }

    @Override
    public Collection<Answer> findAll() {
        return null;
    }

    @SneakyThrows
    @Override
    public Collection<Answer> getByQuestionId(QuestionId questionId) {
        String query = new QueryBuilder().select("*").from("answers").where("fk_question","=").build();

        ResultSet res = super.safeExecuteQuery(query, Arrays.asList(questionId.asString()));

        Collection<Answer> found = new ArrayList<>();

        while (res.next()){

            found.add(this.resultSetToEntity(res));
        }

        return found;
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
