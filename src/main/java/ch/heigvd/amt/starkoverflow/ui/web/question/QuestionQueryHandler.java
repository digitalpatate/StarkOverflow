package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionQueryHandler", urlPatterns = "/question/*")
public class QuestionQueryHandler extends HttpServlet {

    private QuestionService questionService;

    @Override
    public void init() throws ServletException{
        super.init();
        ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
        questionService = serviceRegistry.getQuestionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionService.getQuestions();

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
