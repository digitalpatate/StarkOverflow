package ch.heigvd.amt.starkoverflow.ui.web.logout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutCommandHandler", urlPatterns = "/logout")
public class LogoutCommandHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
       request.getSession().setAttribute("currentUser",null);
       response.sendRedirect("/");
    }
}
