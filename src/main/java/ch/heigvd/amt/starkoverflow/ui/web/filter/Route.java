package ch.heigvd.amt.starkoverflow.ui.web.filter;

import lombok.Getter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Route {

    private String path;

    private List<String> methods;


    public Route(String path, String... methods) {
        this.path = path;
        this.methods = new ArrayList<>();
        this.methods.addAll(Arrays.asList(methods));
    }

    public boolean match(HttpServletRequest req) {
        return req.getRequestURI().equals("/") ||
               req.getRequestURI().startsWith(path) && methods.contains(req.getMethod());
    }
}
