package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.CommentId;
import ch.heigvd.amt.starkoverflow.domain.comment.ICommentRepository;
import ch.heigvd.amt.starkoverflow.domain.tag.Tag;
import ch.heigvd.amt.starkoverflow.domain.tag.TagId;
import lombok.AllArgsConstructor;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcCommentRepository")
//@NoArgsConstructor
@AllArgsConstructor
public class JdbcCommentRepository extends JdbcRepository implements ICommentRepository {

    @Override
    public Collection<Comment> find(CommentQuery query) {
        return null;
    }

    @Override
    public Comment save(Comment entity) {
        String query = String.format("INSERT INTO comments(comment_id, author, content) VALUES(?,?,?)");

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getAuthor().getId().asString());
            statement.setString(3, entity.getContent());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void remove(CommentId id) {
        super.remove("comments", "comment_id", id);
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        Optional<IEntity> comment = super.find("comments", "comment_id", id.asString());

        return comment.map(entity -> Optional.of((Comment) entity)).orElse(null);
    }

    @Override
    public Collection<Comment> findAll() {
        return (Collection) super.findAll("comments");
    }

    @Override
    public Comment resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Comment(
                new CommentId(resultSet.getString("comment_id")),
                resultSet.getString("content")
        );
    }
}
