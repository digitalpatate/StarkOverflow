package ch.heigvd.amt.starkoverflow.ui.web.user;

import ch.heigvd.amt.starkoverflow.application.User.UserService;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.User.dto.UsersDTO;
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

@WebServlet("/users/*")
public class UserQueryHandler extends HttpServlet {
    @Inject @Named("UserService")
    private UserService userService;

    @Inject @Named("QuestionService")
    private QuestionService questionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String[] urlParts = req.getPathInfo().split("/");
        UserDTO userDTO = userService.getByUsername(urlParts[1]);
        QuestionsDTO questionsDTO = questionService.getQuestionsByAuthor(userDTO.getId());
        req.setAttribute("questions",questionsDTO);
        req.setAttribute("user",userDTO);

        req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,res);

    }
}
