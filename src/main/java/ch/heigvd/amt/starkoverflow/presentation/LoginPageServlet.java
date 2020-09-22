package ch.heigvd.amt.starkoverflow.presentation;

import ch.heigvd.amt.starkoverflow.model.LoginCommand;
import ch.heigvd.amt.starkoverflow.model.PersonDTO;

import javax.imageio.spi.ServiceRegistry;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "LoginPageServlet", urlPatterns = "/login.do")
public class LoginPageServlet extends HttpServlet {
    @Inject
    ServiceRegistry serviceRegistry;

    HashMap<String, PersonDTO> loggedUsers = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginCommand command = new LoginCommand();
        command.email = request.getParameter("email").toString();
        command.password = request.getParameter("password").toString();

        PersonDTO user = RegisterPageServlet.users.get(command.email);
        if(user != null){

        } else {
            if(user.password.equals(command.password)){
                response.sendRedirect("/home");
            }
        }
    }
}
