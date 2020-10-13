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
import javax.swing.text.html.Option;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
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
        super.insert("comments", Arrays.asList(
                "comment_id",
                "author",
                "content"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getAuthor().getId().asString(),
                entity.getContent()
        ));

        return entity;
    }

    @Override
    public void remove(CommentId id) {
        super.remove("comments", "comment_id", id);
    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        Optional<IEntity> comment = super.find("comments", "comment_id", id.asString());

        return comment.map(entity -> (Comment) entity);
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
