package ch.heigvd.amt.starkoverflow.ui.web.user;

import ch.heigvd.amt.starkoverflow.application.User.UserService;
import ch.heigvd.amt.starkoverflow.application.User.dto.UsersDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersQueryHandler extends HttpServlet {
    @Inject @Named("UserService")
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UsersDTO usersDTO = userService.getAllUsers();
        req.setAttribute("users",usersDTO);
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req,res);

    }
}
