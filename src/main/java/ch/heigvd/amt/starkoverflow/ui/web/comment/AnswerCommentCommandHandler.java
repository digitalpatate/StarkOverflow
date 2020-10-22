package ch.heigvd.amt.starkoverflow.ui.web.comment;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentService;
import ch.heigvd.amt.starkoverflow.application.Comment.CreateAnswerCommentCommand;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AnswerCommentCommandHandler", urlPatterns = "/comment/answer")
public class AnswerCommentCommandHandler extends HttpServlet {

    @Inject @Named("CommentService")
    CommentService commentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateAnswerCommentCommand command = CreateAnswerCommentCommand.builder()
                .content(req.getParameter("commentContent"))
                .author(userDTO.getId())
                .answerId(req.getParameter("answerId"))
                .build();

        commentService.createAnswerComment(command);

        resp.sendRedirect("/question/" + req.getParameter("questionId"));
    }
}
