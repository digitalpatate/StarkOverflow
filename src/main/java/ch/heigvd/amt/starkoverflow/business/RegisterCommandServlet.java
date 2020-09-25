package ch.heigvd.amt.starkoverflow.business;

import ch.heigvd.amt.starkoverflow.model.PersonDTO;
import ch.heigvd.amt.starkoverflow.model.Users;

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
                .email(request.getParameter("email"))
                .profilePicture(request.getParameter("profilePicture"))
                .name(request.getParameter("name"))
                .surname(request.getParameter("surname"))
                .password(request.getParameter("password"))
                .build();

        if(Users.INSTANCE.addUser(user)){
            request.setAttribute("errors", "Email already taken");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
        Users.INSTANCE.dump();
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
