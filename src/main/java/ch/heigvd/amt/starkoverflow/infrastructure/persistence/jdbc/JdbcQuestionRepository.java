package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.answer.IAnswerRepository;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@ApplicationScoped
@Named("JdbcQuestionRepository")
@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcQuestionRepository extends JdbcRepository implements IQuestionRepository {
    @Inject
    @Named("JdbcTagRepository")
    private ITagRepository tagRepository;

    @Inject
    @Named("JdbcAnswerRepository")
    private JdbcAnswerRepository answerRepository;

    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }

    @Override
    public void addTag(QuestionId questionId, TagId tagId) {
        // FIXME: maybe check if question and tag exists ?
        super.insert("tags_questions", Arrays.asList(
                "fk_tag",
                "fk_question"
        ), Arrays.asList(
                tagId.asString(),
                questionId.asString()
        ));
    }

    @Override
    public Collection<Tag> getQuestionTags(QuestionId questionId) {
        ResultSet res = super.selectWhere("tags_questions", "fk_question", questionId.asString());

        Collection<Tag> tagsFound = new ArrayList<>();

        try {

            while (res.next()){
                Optional<Tag> tag = tagRepository.findById(new TagId(res.getString("fk_tag")));

                if(tag.isPresent()) {
                    tagsFound.add(tag.get());
                } else {
                    // FIXME: add error
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tagsFound;
    }


    @Override
    public Collection<Question> findByAuthor(String authorId) {
        ResultSet res = super.selectWhere("questions","fk_author",authorId);
        Collection<Question> questionsFound = new ArrayList<>();
        try {

            while (res.next()){

                questionsFound.add(this.resultSetToEntity(res));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionsFound;

    }

    @Override
    public Collection<Question> findByTag(String tag) {
        Collection<Question> questions = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT questions.question_id, " +
                            "questions.title, " +
                            "questions.content, " +
                            "questions.creation_date, " +
                            "questions.fk_author " +
                            "FROM questions " +
                            "INNER JOIN tags_questions " +
                            "ON questions.question_id = tags_questions.fk_question " +
                            "INNER JOIN tags " +
                            "ON tags.tag_id = tags_questions.fk_tag " +
                            "WHERE tags.name LIKE ?");

            preparedStatement.setString(1, tag);

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                questions.add(resultSetToEntity(res));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return questions;

    }

    @Override
    public Optional<Answer> getAcceptedAnswer(QuestionId questionId) {
        Optional<Answer> acceptedAnswer = null;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT answers.answer_id, " +
                            "answers.content, " +
                            "answers.creation_date, " +
                            "answers.fk_author, " +
                            "answers.fk_question, " +
                            "answers.approuval_state " +
                            "FROM questions " +
                            "INNER JOIN answers " +
                            "ON questions.question_id = answers.fk_question " +
                            "WHERE answers.approuval_state = true " +
                            "AND questions.question_id = ?");
            preparedStatement.setString(1, questionId.asString());

            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) {
                acceptedAnswer = Optional.of(answerRepository.resultSetToEntity(res));
            } else {
                acceptedAnswer = Optional.empty();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return acceptedAnswer;
    }

    @Override
    public boolean hasAcceptedAnswer(QuestionId questionId) {
        boolean hasAcceptedAnswer = false;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT COUNT(*) > 0 AS has_accepted " +
                            "FROM questions " +
                            "INNER JOIN answers " +
                            "ON questions.question_id = answers.fk_question " +
                            "WHERE answers.approuval_state = true " +
                            "AND questions.question_id = ?");

            preparedStatement.setString(1, questionId.asString());
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) {
                hasAcceptedAnswer = res.getBoolean("has_accepted");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return hasAcceptedAnswer;
    }

    @Override
    public int getTotalQuestion() {
        return 0;
    }

    @Override
    public Question save(Question entity) {
        super.insert("questions",
                Arrays.asList(
                        "question_id",
                        "title",
                        "content",
                        "fk_author"
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
        super.remove("questions", "question_id", id); // FIXME: maybe put those strings as var in superclass
    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        Optional<IEntity> question = super.find("questions", "question_id", id.asString()); // FIXME: maybe put those strings as var in superclass
        return question.map(entity -> (Question) entity);

    }

    @Override
    public Collection<Question> findAll() {
        return (Collection) super.findAll("questions");
    }

    @Override
    public Question resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Question(
                new QuestionId(resultSet.getString("question_id")),
                resultSet.getString("title"),
                resultSet.getString("content"),
                resultSet.getTimestamp("creation_date"),
                new UserId(resultSet.getString("fk_author"))
        );
    }

    @SneakyThrows
    public Collection<Question> resultSetToCollection(ResultSet resultSet){
        Collection<Question> entities = new LinkedList<>();

        while (resultSet.next()){
            entities.add(resultSetToEntity(resultSet));
        }
        return entities;
    }


}
