package ch.heigvd.amt.starkoverflow.ui.web.vote;

import ch.heigvd.amt.starkoverflow.application.Event.EventService;
import ch.heigvd.amt.starkoverflow.application.Event.EventTypes;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.Vote.CreateAnswerVoteCommand;
import ch.heigvd.amt.starkoverflow.application.Vote.VoteService;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.domain.event.Event;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AnswerVoteCommandHandler", urlPatterns = "/vote/answer")
public class AnswerVoteCommandHander extends HttpServlet {

    @Inject @Named("VoteService")
    VoteService voteService;

    @Inject @Named("EventService")
    EventService eventService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateAnswerVoteCommand command = CreateAnswerVoteCommand.builder()
                .userId(userDTO.getId())
                .answerId(req.getParameter("answerId"))
                .build();

        try {
            voteService.createAnswerVote(command);
        }  catch (IllegalArgumentException e) {
            req.getSession().setAttribute("errors", e.getMessage());
            resp.sendRedirect("/question/" + req.getParameter("questionId"));
        }

        Event event = new Event(
                new UserId(userDTO.getId()),
                EventTypes.VOTE_FOR_ANSWER.toString()
        );

        eventService.triggerEvent(event);

        resp.sendRedirect("/question/" + req.getParameter("questionId"));
    }
}
