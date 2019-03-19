package joboonja.filter;

import joboonja.utils.Session;
import joboonja.models.User;
import joboonja.models.UserList;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@Order(1)
@Component
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
