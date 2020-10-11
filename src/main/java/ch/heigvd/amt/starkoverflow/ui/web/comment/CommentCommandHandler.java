package ch.heigvd.amt.starkoverflow.ui.web.comment;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentService;
import ch.heigvd.amt.starkoverflow.application.Comment.CreateCommentCommand;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommentCommandHandler extends HttpServlet {

    @Inject @Named("CommentService")
    CommentService commentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");


        CreateCommentCommand command = CreateCommentCommand.builder()
                .commentableId(req.getParameter("commentable"))
                .content(req.getParameter("content"))
                .author(userDTO.getId())
                .build();

        commentService.createComment(command);

        resp.sendRedirect(req.getParameter("sourcePath"));
    }
}
