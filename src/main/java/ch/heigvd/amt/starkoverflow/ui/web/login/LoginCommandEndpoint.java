package ch.heigvd.amt.starkoverflow.ui.web.login;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticationFailedException;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegistrationFailedException;

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

        CurrentUserDTO currentUser = null;

        AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                .username(request.getParameter("username"))
                .clearTextPassword(request.getParameter("password"))
                .build();
        try {
            currentUser = identityManagementFacade.authenticate(authenticateCommand);
            request.getSession().setAttribute("currentUser", currentUser);
            String targetUrl = request.getSession().getAttribute("targetUrl").toString();
            targetUrl = (targetUrl != null) ? targetUrl : "/";
            response.sendRedirect(targetUrl);
            return;
        } catch (AuthenticationFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect("/starkOverflow/login");
            return;
        }
    }
}
