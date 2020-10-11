package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

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

        QuestionId id = new QuestionId(urlParts[1]);
        QuestionDTO questionDTO = questionService.getQuestion(id);

        if(questionDTO != null) {
            req.setAttribute("question", questionDTO);
            req.getRequestDispatcher("/WEB-INF/views/question.jsp").forward(req,res);
        } else {
            res.sendRedirect("/");
        }
    }
}
