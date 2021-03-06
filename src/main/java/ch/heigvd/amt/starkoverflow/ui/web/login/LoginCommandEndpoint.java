package ch.heigvd.amt.starkoverflow.ui.web.login;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.IdentityManagementService;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.starkoverflow.application.identitymgmt.authenticate.AuthenticationFailedException;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import java.io.IOException;

@WebServlet(name = "LoginCommandEndpoint", urlPatterns = "/login")
@Log
public class LoginCommandEndpoint extends HttpServlet {

    @Inject @Named("IdentityManagementService")
    private IdentityManagementService identityManagementService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object errors = request.getSession().getAttribute("errors");
        request.setAttribute("errors", errors);
        request.getSession().removeAttribute("errors");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().removeAttribute("errors");

        AuthenticateCommand command = AuthenticateCommand.builder()
                .email(request.getParameter("email"))
                .clearTextPassword(request.getParameter("password"))
                .build();

        try {
            UserDTO currentUser = identityManagementService.authenticate(command);
            request.getSession().setAttribute("currentUser", currentUser);
            response.sendRedirect("/profile");
        } catch (NotFoundException e) {
            log.info("Authentification failed! Email " + command.getEmail() + " not found");
            request.getSession().setAttribute("errors", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } catch (AuthenticationFailedException e) {
            log.info("Authentification failed for "+ command.getEmail());
            request.getSession().setAttribute("errors", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
