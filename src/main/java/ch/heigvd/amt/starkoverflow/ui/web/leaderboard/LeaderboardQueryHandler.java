package ch.heigvd.amt.starkoverflow.ui.web.leaderboard;

import ch.heigvd.amt.starkoverflow.application.leaderboard.LeaderboardService;
import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.LeaderBoardDTO_game;
import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.PagableLeaderboardDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LeaderboardQueryHandler", urlPatterns = "/leaderboard/*")
public class LeaderboardQueryHandler extends HttpServlet {


    @Inject
    @Named("LeaderboardService")
    private LeaderboardService leaderboardService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String[] urlParts = req.getPathInfo().split("/");

        String pointScaleName = urlParts[1];

        PagableLeaderboardDTO leaderboardDTO = leaderboardService.getLeaderBoardFromPointScaleName(pointScaleName);

        System.out.println(leaderboardDTO);
        req.setAttribute("pointScaleName", pointScaleName);
        req.setAttribute("leaderboard", leaderboardDTO);

        req.getRequestDispatcher("/WEB-INF/views/leaderboard.jsp").forward(req,res);

    }
}
