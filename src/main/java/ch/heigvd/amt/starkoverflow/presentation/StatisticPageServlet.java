package ch.heigvd.amt.starkoverflow.presentation;

import ch.heigvd.amt.starkoverflow.model.Statistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatisticPageServlet", urlPatterns = "/statistic")
public class StatisticPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Statistic sta = Statistic.builder().title("Statistic").data("DATA_DATa_DAta_Data_data_datA_daTA_dATA_DATA").build();
        request.setAttribute("statistic",sta);

        request.getRequestDispatcher("/WEB-INF/views/statistic.jsp").forward(request, response);
    }

}
