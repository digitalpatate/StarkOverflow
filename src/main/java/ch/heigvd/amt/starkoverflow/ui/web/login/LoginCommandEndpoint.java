package ch.heigvd.amt.starkoverflow.ui.web.login;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.IdentityManagementService;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticationFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommandEndpoint", urlPatterns = "/login")
public class LoginCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private IdentityManagementService identityManagementService = serviceRegistry.getIdentityManagementFacade();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object errors = request.getSession().getAttribute("errors");
        request.setAttribute("errors", errors);
        request.getSession().removeAttribute("errors");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("errors");

        UserDTO currentUser = null;

        AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                .email(request.getParameter("email"))
                .clearTextPassword(request.getParameter("password"))
                .build();
        try {
            currentUser = identityManagementService.authenticate(authenticateCommand);
            request.getSession().setAttribute("currentUser", currentUser);
            response.sendRedirect("/profile");
        } catch (AuthenticationFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect("/login");
        }
    }
}
