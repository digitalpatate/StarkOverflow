package ch.heigvd.amt.starkoverflow.business;

import ch.heigvd.amt.starkoverflow.model.PersonDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "RegisterCommandServlet", urlPatterns = "/register.do")
public class RegisterCommandServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDTO user = PersonDTO.builder()
                .email(request.getAttribute("email").toString())
                .password(request.getAttribute("password").toString())
                .build();

        response.sendRedirect("/login");
    }
}
