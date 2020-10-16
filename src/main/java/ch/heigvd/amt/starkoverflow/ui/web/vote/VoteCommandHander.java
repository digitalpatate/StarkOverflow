package ch.heigvd.amt.starkoverflow.ui.web.vote;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.Vote.CreateVoteCommand;
import ch.heigvd.amt.starkoverflow.application.Vote.VoteService;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="VoteCommandHandler", urlPatterns = "/vote")
public class VoteCommandHander extends HttpServlet {

    @Inject @Named("VoteService")
    VoteService voteService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateVoteCommand command = CreateVoteCommand.builder()
                .userId(userDTO.getId())
                .answerId(req.getParameter("answerId"))
                .build();

        try {
            voteService.createVote(command);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/question/" + req.getParameter("questionId"));
    }
}
