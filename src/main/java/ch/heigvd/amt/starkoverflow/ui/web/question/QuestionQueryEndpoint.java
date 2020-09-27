package ch.heigvd.amt.starkoverflow.ui.web.question;


import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.question.QuestionFacade;
import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.question.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/questions")
public class QuestionQueryEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry;

    private QuestionFacade questionFacade;

    @Override
    public void init() throws ServletException{
        super.init();
        serviceRegistry = ServiceRegistry.getServiceRegistry();
        questionFacade = serviceRegistry.questionFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionFacade.getQuestion(QuestionQuery.builder().build());

        req.setAttribute("questions",questionsDTO);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,res);

    }
}
