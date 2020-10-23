package ch.heigvd.amt.starkoverflow.ui.web.statistic;

import ch.heigvd.amt.starkoverflow.application.statistic.StatisticService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticQueryHandler", urlPatterns = "/statistic")
public class StatisticQueryHandler extends HttpServlet {

    @Inject @Named("StatisticService")
    private StatisticService statisticService;

    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nbUsers = statisticService.getNbUsers();
        int nbQuestions = statisticService.getNbQuestions();
        int nbAnswers = statisticService.getNbAnswers();
        int nbComments = statisticService.getNbComments();
        int nbVotes = statisticService.getNbVotes();
        request.setAttribute("nbUsers", nbUsers);
        request.setAttribute("nbQuestions", nbUsers); //TODO
        request.setAttribute("nbAnswers", nbUsers); //TODO
        request.setAttribute("nbComments", nbUsers); //TODO
        request.setAttribute("nbVotes", nbUsers); //TODO
        request.getRequestDispatcher("/WEB-INF/views/statistic.jsp").forward(request, response);
    }

}
