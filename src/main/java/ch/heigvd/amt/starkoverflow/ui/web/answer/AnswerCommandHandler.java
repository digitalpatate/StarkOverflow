package ch.heigvd.amt.starkoverflow.ui.web.answer;

import ch.heigvd.amt.starkoverflow.application.Answer.AnswerService;
import ch.heigvd.amt.starkoverflow.application.Answer.CreateAnswerCommand;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AnswerCommandHandler extends HttpServlet {

    @Inject @Named("AnswerService")
    AnswerService answerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateAnswerCommand command = CreateAnswerCommand.builder()
                .author(userDTO.getId())
                .question(req.getParameter("question"))
                .content(req.getParameter("content"))
                .build();

        answerService.createAnswer(command);

        resp.sendRedirect(req.getParameter("sourcePath"));
    }
}
