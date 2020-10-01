package ch.heigvd.amt.starkoverflow.ui.web.statistic;

import ch.heigvd.amt.starkoverflow.application.question.QuestionQuery;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.model.Statistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticPageServlet", urlPatterns = "/statistic")
public class StatisticQueryHandler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statistic sta = Statistic.builder().title("Statistic").data("my stats!").build();
        request.setAttribute("statistic",sta);

        request.getRequestDispatcher("/WEB-INF/views/statistic.jsp").forward(request, response);

        //Update query with string param
        /*QuestionsDTO questionsDTO = questionService.getQuestion(QuestionQuery.builder().build());

        req.setAttribute("questions",questionsDTO);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,res);*/
    }

}
