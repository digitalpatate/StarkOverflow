package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.question.IQuestionRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.tag.ITagRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

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
@Named("JdbcQuestionRepository")
@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcQuestionRepository extends JdbcRepository implements IQuestionRepository {
    @Inject
    @Named("JdbcTagRepository")
    private ITagRepository tagRepository;

    @Override
    public Collection<Question> find(QuestionQuery query) {
        return null;
    }

    @Override
    public void addTag(QuestionId questionId, TagId tagId) {
        // FIXME: maybe check if question and tag exists ?
        super.insert("tags_questions", Arrays.asList(
            "tag_id",
            "question_id"
        ), Arrays.asList(
            tagId.asString(),
            questionId.asString()
        ));
    }

    @Override
    public Collection<Tag> getQuestionTags(QuestionId questionId) {
        PreparedStatement preparedStatement = super.selectWhere("tags_questions", "question_id", questionId.asString());

        Collection<Tag> tagsFound = new ArrayList<>();

        try {
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()){
                Optional<Tag> tag = tagRepository.findById(new TagId(res.getString("tag_id")));

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
    public Question save(Question entity) {
        super.insert("questions",
                Arrays.asList(
                        "question_id",
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
        super.remove("questions", "question_id", id); // FIXME: maybe put those strings as var in superclass
    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        Optional<IEntity> question = super.find("questions", "question_id", id.asString()); // FIXME: maybe put those strings as var in superclass

        return question.map(entity -> Optional.of((Question) entity)).orElse(null);
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
                resultSet.getDate("creation_date"),
                new UserId(resultSet.getString("author"))
        );
    }
}