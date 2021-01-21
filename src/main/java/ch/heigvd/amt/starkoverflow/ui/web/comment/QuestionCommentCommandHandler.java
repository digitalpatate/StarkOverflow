package ch.heigvd.amt.starkoverflow.ui.web.comment;

import ch.heigvd.amt.starkoverflow.application.Comment.CommentService;
import ch.heigvd.amt.starkoverflow.application.Comment.CreateAnswerCommentCommand;
import ch.heigvd.amt.starkoverflow.application.Comment.CreateQuestionCommentCommand;
import ch.heigvd.amt.starkoverflow.application.Event.EventService;
import ch.heigvd.amt.starkoverflow.application.Event.EventTypes;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.event.Event;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;

@WebServlet(name="QuestionCommentCommandHandler", urlPatterns = "/comment/question")
public class QuestionCommentCommandHandler extends HttpServlet {

    @Inject @Named("CommentService")
    CommentService commentService;

    @Inject @Named("EventService")
    EventService eventService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateQuestionCommentCommand command = CreateQuestionCommentCommand.builder()
                .content(req.getParameter("commentContent"))
                .author(userDTO.getId())
                .questionId(req.getParameter("questionId"))
                .build();

        commentService.createQuestionComment(command);

        Event event = new Event(
                new UserId(userDTO.getId()),
                EventTypes.COMMENT_A_QUESTION.toString()
        );

        eventService.triggerEvent(event);

        resp.sendRedirect("/question/" + req.getParameter("questionId"));
    }
}
