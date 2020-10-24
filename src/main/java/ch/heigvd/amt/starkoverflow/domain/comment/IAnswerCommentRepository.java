package ch.heigvd.amt.starkoverflow.domain.comment;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;

import java.util.Collection;

public interface IAnswerCommentRepository extends IRepository<Comment, CommentId> {
    Collection<Comment> find(CommentQuery query);
    Collection<Comment> getCommentsOnAnswer(AnswerId id);

    int getTotalAnswerComment();
}
