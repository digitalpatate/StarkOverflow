package ch.heigvd.amt.starkoverflow.ui.web.profile;

import ch.heigvd.amt.starkoverflow.application.User.UserService;
import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileQueryHandler extends HttpServlet {

    @Inject @Named("UserService")
    private UserService userService;

    @Override
    public void init() throws ServletException{
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        request.setAttribute("currentUser", userDTO);
        request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
    }
}
