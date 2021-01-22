package ch.heigvd.amt.starkoverflow.ui.web.profile;


import ch.heigvd.amt.starkoverflow.application.Reputation.ReputationDTO;
import ch.heigvd.amt.starkoverflow.domain.UserId;
import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.GamificatorService;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileQueryHandler extends HttpServlet {
    @Inject @Named("QuestionService")
    private QuestionService questionService;
    @Inject @Named("GamificatorService")
    private GamificatorService gamificatorService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("currentUser");
        ReputationDTO reputationDTO = new ReputationDTO();
        try {
            reputationDTO = gamificatorService.getReputation(new UserId(userDTO.getId()));

        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        QuestionsDTO questionsDTO = questionService.getQuestionsByAuthor(userDTO.getId());

        System.out.println(reputationDTO);

        request.setAttribute("questions",questionsDTO);
        request.setAttribute("user",userDTO);
        request.setAttribute("badges", reputationDTO.getBadgesReward());
        request.setAttribute("pointRewards", reputationDTO.getPointsReward());

        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request,response);
    }
}
