package ch.heigvd.amt.starkoverflow.ui.web.login;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticationFailedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommandEndpoint", urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {

    private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    private IdentityManagementFacade identityManagementFacade = serviceRegistry.getIdentityManagementFacade();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("errors");

        UserDTO currentUser = null;

        AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                .email(request.getParameter("email"))
                .clearTextPassword(request.getParameter("password"))
                .build();
        try {
            currentUser = identityManagementFacade.authenticate(authenticateCommand);
            request.getSession().setAttribute("currentUser", currentUser);
            //String targetUrl = request.getSession().getAttribute("targetUrl").toString();
            //targetUrl = (targetUrl != null) ? targetUrl : "/";
            //response.sendRedirect(targetUrl);
            response.sendRedirect("/starkOverflow/profile");
        } catch (AuthenticationFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect("/starkOverflow/login");
        }
    }
}
