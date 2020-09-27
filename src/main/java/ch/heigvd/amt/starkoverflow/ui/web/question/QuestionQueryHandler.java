package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionQueryHandler",urlPatterns = "/questions")
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

        //Update query with string param
        QuestionsDTO questionsDTO = questionService.getQuestion(QuestionQuery.builder().build());

        req.setAttribute("questions",questionsDTO);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,res);

    }
}
