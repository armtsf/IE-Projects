package filter;

import base.Session;
import models.User;
import models.UserList;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@WebFilter(urlPatterns = {"/project", "/project/*", "/user/*"})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userId = Session.get("userId");
        if (userId == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        try {
            User user = UserList.get(userId);
            servletRequest.setAttribute("user", user);
            servletRequest.setCharacterEncoding("UTF-8");
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (NoSuchElementException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
