package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.RestService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionQueryHandler", urlPatterns = "/question/*")
public class QuestionQueryHandler extends HttpServlet {

    @Inject @Named("QuestionService")
    private QuestionService questionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String[] urlParts = req.getPathInfo().split("/");

        UserDTO viewer = (UserDTO) req.getSession().getAttribute("currentUser");

        QuestionId id = new QuestionId(urlParts[1]);

        UserId viewerId = null;

        if(viewer != null) {
            viewerId = new UserId(viewer.getId());
        }

        QuestionDTO questionDTO = questionService.getQuestion(id, viewerId);

        if(questionDTO != null) {
            req.setAttribute("currentUser", viewer);
            req.setAttribute("question", questionDTO);
            req.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(req,res);
        }
    }


}
