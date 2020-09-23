package ch.heigvd.amt.starkoverflow.presentation;

import ch.heigvd.amt.starkoverflow.model.Profile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfilePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Profile profile = new Profile("Bob");
        req.setAttribute("profile", profile);
        req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,res);

    }
}
