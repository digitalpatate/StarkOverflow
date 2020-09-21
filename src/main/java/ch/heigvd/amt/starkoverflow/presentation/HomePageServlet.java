package ch.heigvd.amt.starkoverflow.presentation;

import ch.heigvd.amt.starkoverflow.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "HomePageServlet")
public class HomePageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Question> questions = new LinkedList<>();
        // TODO: récupérer les questions devra se faire dans business
        questions.add(Question.builder().title("Ma question").content("mon contenu").build());
        questions.add(Question.builder().title("Ma question 2").content("mon contenu 2").build());
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
    }
}
