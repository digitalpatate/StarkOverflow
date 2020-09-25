package ch.heigvd.amt.starkoverflow.business;

import ch.heigvd.amt.starkoverflow.model.LoginCommand;
import ch.heigvd.amt.starkoverflow.model.PersonDTO;
import ch.heigvd.amt.starkoverflow.model.Users;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommandServlet", urlPatterns = "/login.do")
public class LoginCommandServlet extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginCommand command = LoginCommand.builder()
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .build();

        PersonDTO loggedInUser = null;
        try {
            loggedInUser = Users.INSTANCE.login(command);
            request.getSession().setAttribute("currentUser", loggedInUser);
            String targetUrl = (String) request.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : "home";
            response.sendRedirect(targetUrl);
        } catch (Exception e) {
            request.setAttribute("errors", "Invalid login");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
