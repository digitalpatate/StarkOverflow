package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.comment.ICommentRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.CommentId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryCommentRepository implements ICommentRepository {

    private Map<CommentId,Comment> store = new ConcurrentHashMap<>();

    @Override
    public void save(Comment comment) {
        store.put(comment.getId(),comment);
    }

    @Override
    public void remove(CommentId commentId) {
        store.remove(commentId);
    }

    @Override
    public Optional<Comment> findById(CommentId commentId) {
        Comment comment = store.get(commentId);
        if(comment == null){
            return Optional.empty();
        }
        return Optional.of(comment);
    }

    @Override
    public Collection<Comment> findAll() {
        return new ArrayList<>(store.values());
    }


    @Override
    public Collection<Comment> find(CommentQuery query) {
        return findAll();
    }
}
