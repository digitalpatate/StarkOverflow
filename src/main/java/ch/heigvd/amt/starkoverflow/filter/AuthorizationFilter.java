package ch.heigvd.amt.starkoverflow.filter;

import ch.heigvd.amt.starkoverflow.model.PersonDTO;

import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/patate")
public class AuthorizationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(isPublicResource(request.getRequestURI())){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        PersonDTO loggedInUser = (PersonDTO) request.getSession().getAttribute("currentUser");

        if(loggedInUser == null){
            String targetUrl = request.getRequestURI();
            if(request.getQueryString() != null){
                targetUrl = "/" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);

            ((HttpServletResponse) servletResponse).sendRedirect("/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isPublicResource(String requestURI) {
        /*if(requestURI.startsWith("/assets")){
            return true;
        }
        if(requestURI.startsWith("/login")){
            return true;
        }
        if(requestURI.startsWith("/logout")){
            return true;
        }
        if(requestURI.startsWith("/register")){
            return true;
        }
        return false;*/
        return true;
    }

    public void destroy() {

    }
}
