package ch.heigvd.amt.starkoverflow.ui.web.statistic;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.statistic.StatisticQuery;
import ch.heigvd.amt.starkoverflow.application.statistic.StatisticService;
import ch.heigvd.amt.starkoverflow.application.statistic.dto.StatisticDTO;
import ch.heigvd.amt.starkoverflow.application.statistic.dto.StatisticsDTO;
import ch.heigvd.amt.starkoverflow.domain.statistic.Statistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticQueryHandler", urlPatterns = "/statistic")
public class StatisticQueryHandler extends HttpServlet {

    private StatisticService statisticService;

    @Override
    public void init() throws ServletException{
        super.init();
        ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
        statisticService = serviceRegistry.getStatisticService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatisticsDTO statisticsDTO = statisticService.getStatistic(StatisticQuery.builder().build());
        request.setAttribute("statistics", statisticsDTO);
        request.getRequestDispatcher("/WEB-INF/views/statistic.jsp").forward(request, response);
    }

}
