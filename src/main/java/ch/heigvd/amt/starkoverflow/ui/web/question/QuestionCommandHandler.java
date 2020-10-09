package ch.heigvd.amt.starkoverflow.ui.web.question;

import ch.heigvd.amt.starkoverflow.application.question.CreateQuestionCommand;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="QuestionCommandHandler", urlPatterns = "/question.do")
public class QuestionCommandHandler extends HttpServlet {

    @Inject @Named("QuestionService")
    private QuestionService questionService;

    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        CreateQuestionCommand createQuestionCommand = CreateQuestionCommand.builder()
                .content(req.getParameter("questionContent"))
                .title(req.getParameter("questionTitle"))
                //.userId(new UserId())
                .build();

        questionService.createQuestion(createQuestionCommand);
        res.sendRedirect("");
    }
}
