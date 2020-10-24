package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.CommentId;
import ch.heigvd.amt.starkoverflow.domain.comment.IAnswerCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.IQuestionCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;

@ApplicationScoped
@Named("InMemoryQuestionCommentRepository")
public class InMemoryQuestionCommentRepository extends InMemoryRepository<Comment,CommentId> implements IQuestionCommentRepository {

    @Override
    public Collection<Comment> find(CommentQuery query) {
        return null;
    }

    @Override
    public Collection<Comment> getCommentsOnQuestion(QuestionId questionId) {
        return null;
    }

    @Override
    public int getTotalQuestionCommentRepository() {
        return 0;
    }
}
