package ch.heigvd.amt.starkoverflow.ui.web;

import ch.heigvd.amt.starkoverflow.model.LoginCommand;
import ch.heigvd.amt.starkoverflow.model.PersonDTO;
import ch.heigvd.amt.starkoverflow.business.UsersManager;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginCommandServlet", urlPatterns = "/login.do")
public class LoginCommandHandlerServlet extends HttpServlet {
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
            loggedInUser = UsersManager.INSTANCE.login(command);
            request.getSession().setAttribute("currentUser", loggedInUser);
            String targetUrl = (String) request.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : "";
            response.sendRedirect(targetUrl);
        } catch (Exception e) {
            request.setAttribute("errors", "Invalid login");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
