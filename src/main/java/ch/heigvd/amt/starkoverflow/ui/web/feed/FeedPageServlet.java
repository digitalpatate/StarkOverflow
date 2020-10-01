package ch.heigvd.amt.starkoverflow.ui.web.feed;

        import ch.heigvd.amt.starkoverflow.domain.question.Question;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.LinkedList;
        import java.util.List;

@WebServlet(name = "FeedPageServlet", urlPatterns = "/feed")
public class FeedPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Hello from feed !!!!!");

        List<Question> questions = new LinkedList<>();
        // TODO: récupérer les questions devra se faire dans business
        questions.add(Question.builder().title("Ma question").content("mon contenu").build());
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("/WEB-INF/views/feed.jsp").forward(request, response);
    }
}
