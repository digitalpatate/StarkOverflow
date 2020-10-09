package ch.heigvd.amt.starkoverflow.ui.web.login;

import ch.heigvd.amt.starkoverflow.application.ServiceRegistry;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.login.RegistrationFailedException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterCommandEndpoint", urlPatterns = "/register")
public class RegisterCommandEndpoint extends HttpServlet {

    //private ServiceRegistry serviceRegistry = ServiceRegistry.getServiceRegistry();
    @Inject @Named("IdentityManagementFacade")
    private IdentityManagementFacade identityManagementFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("errors");

        RegisterCommand registerCommand = RegisterCommand.builder()
            .username(request.getParameter("username"))
            .clearTextPassword(request.getParameter("password"))
            .profilePicture(request.getParameter("profilePicture"))
            .firstname(request.getParameter("firstname"))
            .lastname(request.getParameter("lastname"))
            .email(request.getParameter("email"))
            .build();

        try {
            identityManagementFacade.register(registerCommand);
            response.sendRedirect("/starkOverflow/login");
        } catch (RegistrationFailedException e) {
            request.getSession().setAttribute("errors", e.getMessage());
            response.sendRedirect("/starkOverflow/register");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object errors = request.getSession().getAttribute("errors");
        request.setAttribute("errors", errors);
        request.getSession().removeAttribute("errors");
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }
}

