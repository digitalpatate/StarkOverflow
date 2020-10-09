package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.comment.ICommentRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.CommentId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;

@ApplicationScoped
@Named("InMemoryCommentRepository")
public class InMemoryCommentRepository extends InMemoryRepository<Comment,CommentId> implements ICommentRepository {


    @Override
    public Collection<Comment> find(CommentQuery query) {
        return null;
    }
}
