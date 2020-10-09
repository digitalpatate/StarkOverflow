package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionsQueryHandler", urlPatterns = "/questions")
public class QuestionsHandler extends HttpServlet {

    @Inject @Named("QuestionService")
    private QuestionService questionService;

    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        QuestionsDTO questionsDTO = questionService.getQuestions();

        req.setAttribute("questions", questionsDTO);

        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req,res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");

        CreateQuestionCommand createQuestionCommand = CreateQuestionCommand.builder()
                .content(req.getParameter("questionContent"))
                .title(req.getParameter("questionTitle"))
                .userId(new UserId(userDTO.getId()))
                .build();

        questionService.createQuestion(createQuestionCommand);
        res.sendRedirect("");
    }
}
