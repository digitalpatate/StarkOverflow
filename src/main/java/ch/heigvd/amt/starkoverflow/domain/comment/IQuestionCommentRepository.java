package ch.heigvd.amt.starkoverflow.domain.comment;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentQuery;
import ch.heigvd.amt.starkoverflow.domain.IRepository;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

import java.util.Collection;

public interface IQuestionCommentRepository extends IRepository<Comment, CommentId> {
    Collection<Comment> find(CommentQuery query);
    Collection<Comment> getCommentsOnQuestion(QuestionId questionId);

    int getTotalQuestionCommentRepository();
}
