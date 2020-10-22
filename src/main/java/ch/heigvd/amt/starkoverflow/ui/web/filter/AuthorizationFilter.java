package ch.heigvd.amt.starkoverflow.ui.web.filter;

import ch.heigvd.amt.starkoverflow.application.User.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {

    List<Route> publicRoutes;

    @Override
    public void init(FilterConfig filterConfig) {

        this.publicRoutes = new LinkedList<>();

        this.publicRoutes.add(new Route("/questions","GET"));
        this.publicRoutes.add(new Route("/question","GET"));
        this.publicRoutes.add(new Route("/login","GET","POST"));
        this.publicRoutes.add(new Route("/register","GET","POST"));
        this.publicRoutes.add(new Route("/logout","POST"));
        this.publicRoutes.add(new Route("/assets","GET"));
        this.publicRoutes.add(new Route("/users","GET"));
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;


        Optional<Route> oRoute = publicRoutes.stream().filter(route -> route.match(request)).collect(Collectors.toList()).stream().findFirst();

        if(oRoute.isEmpty() && !userHasSession(request)){
                ((HttpServletResponse) res).sendRedirect("/login");
                return;
        }
        chain.doFilter(req,res);
    }

    private boolean userHasSession(HttpServletRequest request){
        UserDTO loggedInUser = (UserDTO) request.getSession().getAttribute("currentUser");
        return loggedInUser != null;
    }

}
