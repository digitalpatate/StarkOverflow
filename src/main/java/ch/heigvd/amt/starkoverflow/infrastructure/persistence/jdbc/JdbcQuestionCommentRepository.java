package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.CommentId;
import ch.heigvd.amt.starkoverflow.domain.comment.IQuestionCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcQuestionCommentRepository")
//@NoArgsConstructor
@AllArgsConstructor
public class JdbcQuestionCommentRepository extends JdbcRepository implements IQuestionCommentRepository {

    @Override
    public Collection<Comment> find(CommentQuery query) {
        return null;
    }

    @Override
    public Collection<Comment> getCommentsOnQuestion(QuestionId questionId) {
        ResultSet res = super.selectWhere("question_comments", "fk_question", questionId.asString());

        Collection<Comment> commentsFound = new ArrayList<>();

        try {
            while (res.next()) {
                commentsFound.add(resultSetToEntity(res));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentsFound;
    }

    @Override
    public int getTotalQuestionCommentRepository() {
        int nbQuestionComment = -1;
        try {
            ResultSet resultSet = safeExecuteQuery("SELECT COUNT(*) AS totalQuestionComment FROM question_comments",null);
            if(resultSet.next()) {
                nbQuestionComment = resultSet.getInt("totalQuestionComment");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nbQuestionComment;
    }

    @Override
    public Comment save(Comment entity) {
        super.insert("question_comments", Arrays.asList(
                "comment_id",
                "fk_author",
                "content",
                "fk_question"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getAuthor().asString(),
                entity.getContent(),
                entity.getCommentableId().asString()
        ));

        return entity;
    }

    @Override
    public void remove(CommentId id) {
        super.remove("question_comments", "comment_id", id);
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        Optional<IEntity> comment = super.find("question_comments", "comment_id", id.asString());

        return comment.map(entity -> (Comment) entity);
    }

    @Override
    public Collection<Comment> findAll() {
        return (Collection) super.findAll("question_comments");
    }

    @Override
    public Comment resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Comment(
                new CommentId(resultSet.getString("comment_id")),
                resultSet.getString("content"),
                new UserId(resultSet.getString("fk_author")),
                new Question(new QuestionId(resultSet.getString("fk_question")))
        );
    }
}