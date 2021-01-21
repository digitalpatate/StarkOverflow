package ch.heigvd.amt.starkoverflow.ui.web.home;

import ch.heigvd.amt.starkoverflow.application.Tag.TagService;
import ch.heigvd.amt.starkoverflow.application.Tag.dto.TagsDTO;
import ch.heigvd.amt.starkoverflow.application.question.QuestionService;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionDTO;
import ch.heigvd.amt.starkoverflow.application.question.dto.QuestionsDTO;
import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.RestService;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="HomeQueryHandler", urlPatterns = "")
@Log
public class HomeQueryHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.getRequestDispatcher("/questions").forward(req,res);
    }
}
