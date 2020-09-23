package ch.heigvd.amt.starkoverflow.presentation;

import ch.heigvd.amt.starkoverflow.model.PersonDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "RegisterPageServlet", urlPatterns = "/register.do")
public class RegisterPageServlet extends HttpServlet {
    public HashMap<String, PersonDTO> users = new HashMap<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDTO user = PersonDTO.builder()
                .email(request.getAttribute("email").toString())
                .password(request.getAttribute("password").toString()).build();

        users.put(user.email, user);
        response.sendRedirect("/login");
    }
}
