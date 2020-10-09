package ch.heigvd.amt.starkoverflow.application.Comment;

import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswerDTO;
import ch.heigvd.amt.starkoverflow.application.Answer.dto.AnswersDTO;
import ch.heigvd.amt.starkoverflow.application.Comment.dto.CommentDTO;
import ch.heigvd.amt.starkoverflow.application.Comment.dto.CommentsDTO;
import ch.heigvd.amt.starkoverflow.domain.comment.Comment;
import ch.heigvd.amt.starkoverflow.domain.comment.ICommentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class CommentService {
    @Inject
    private ICommentRepository commentRepository;



    public void createComment(CreateCommentCommand command){
        Comment comment = command.createEntity();
        commentRepository.save(comment);
    }

    public CommentsDTO getComments(CommentQuery query){
        Collection<Comment> comments = commentRepository.find(query);

        List<CommentDTO> commentsDTO = comments.stream().map(answer -> CommentDTO.builder().build()).collect(Collectors.toList());

        return CommentsDTO.builder().comments(commentsDTO).build();
    }
}
