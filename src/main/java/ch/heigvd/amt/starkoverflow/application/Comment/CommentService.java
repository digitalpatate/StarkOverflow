package ch.heigvd.amt.starkoverflow.application.Comment;

import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.IAnswerCommentRepository;
import ch.heigvd.amt.starkoverflow.domain.comment.IQuestionCommentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@NoArgsConstructor
@AllArgsConstructor
@Named("CommentService")
@ApplicationScoped
public class CommentService {
    @Inject @Named("JdbcQuestionCommentRepository")
    private IQuestionCommentRepository questionCommentRepository;

    @Inject @Named("JdbcAnswerCommentRepository")
    private IAnswerCommentRepository answerCommentRepository;

    public void createAnswerComment(CreateAnswerCommentCommand command){
        Comment comment = command.createEntity();
        answerCommentRepository.save(comment);
    }

    public void createQuestionComment(CreateQuestionCommentCommand command){
        Comment comment = command.createEntity();
        questionCommentRepository.save(comment);
    }

    /*public CommentsDTO getComments(CommentQuery query){
        Collection<Comment> comments = commentRepository.find(query);

        List<CommentDTO> commentsDTO = comments.stream().map(answer -> CommentDTO.builder().build()).collect(Collectors.toList());

        return CommentsDTO.builder().comments(commentsDTO).build();
    }*/
}
