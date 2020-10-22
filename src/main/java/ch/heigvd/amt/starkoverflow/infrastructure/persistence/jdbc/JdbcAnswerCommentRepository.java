package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.CommentId;
import ch.heigvd.amt.starkoverflow.domain.comment.IAnswerCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import lombok.AllArgsConstructor;
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
@Named("JdbcAnswerCommentRepository")
//@NoArgsConstructor
@AllArgsConstructor
public class JdbcAnswerCommentRepository extends JdbcRepository implements IAnswerCommentRepository {

    @Override
    public Collection<Comment> find(CommentQuery query) {
        return null;
    }

    @Override
    public Comment save(Comment entity) {
        super.insert("answer_comments", Arrays.asList(
                "comment_id",
                "fk_author",
                "content",
                "fk_answer"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getAuthor().asString(),
                entity.getContent(),
                entity.getCommentableId().asString()
        ));

        return entity;
    }

    @Override
    public Collection<Comment> getCommentsOnAnswer(AnswerId answerId) {
        PreparedStatement preparedStatement = super.selectWhere("answer_comments", "fk_answer", answerId.asString());

        Collection<Comment> commentsFound = new ArrayList<>();

        try {
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                commentsFound.add(resultSetToEntity(res));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentsFound;
    }

    @Override
    public void remove(CommentId id) {
        super.remove("answer_comments", "comment_id", id);
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        Optional<IEntity> comment = super.find("answer_comments", "comment_id", id.asString());

        return comment.map(entity -> (Comment) entity);
    }

    @Override
    public Collection<Comment> findAll() {
        return (Collection) super.findAll("answer_comments");
    }

    @Override
    public Comment resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Comment(
                new CommentId(resultSet.getString("comment_id")),
                resultSet.getString("content"),
                new UserId(resultSet.getString("fk_author")),
                new Answer(new AnswerId(resultSet.getString("fk_answer")))
        );
    }
}