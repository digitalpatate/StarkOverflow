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
        //StatisticsDTO statisticsDTO = statisticService.getStatistic(StatisticQuery.builder().build());
        //request.setAttribute("statistics", statisticsDTO.getStatistics());
        request.getRequestDispatcher("/WEB-INF/views/statistic.jsp").forward(request, response);
    }

}
