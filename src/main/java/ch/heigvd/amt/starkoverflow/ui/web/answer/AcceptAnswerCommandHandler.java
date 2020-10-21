package ch.heigvd.amt.starkoverflow.ui.web.answer;

import ch.heigvd.amt.starkoverflow.application.Answer.AcceptAnswerCommand;
import ch.heigvd.amt.starkoverflow.application.Answer.AnswerService;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AcceptAnswerCommandHandler", urlPatterns = "/answer/accept")
public class AcceptAnswerCommandHandler extends HttpServlet {

    @Inject
    @Named("AnswerService")
    AnswerService answerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        AcceptAnswerCommand command = AcceptAnswerCommand.builder()
                .userId(userDTO.getId())
                .answerId(req.getParameter("answerId"))
                .build();

        answerService.acceptAnswer(command);

        resp.sendRedirect("/question/" + req.getParameter("questionId"));
    }
}