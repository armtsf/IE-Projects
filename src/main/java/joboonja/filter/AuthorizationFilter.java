package joboonja.filter;

import joboonja.utils.Session;
import joboonja.models.User;
import joboonja.models.UserRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@Order(1)
@Component
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getServletPath();
        String method = request.getMethod();
        if (path.equals("/users/login") || (path.equals("/users") && method.equals("POST"))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // TODO
        try {
            UserRepository userRepository = new UserRepository();
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Integer userId = Session.get("userId");
            if (userId == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            try {
                User user = userRepository.get(userId.intValue());
                servletRequest.setAttribute("user", user);
                servletRequest.setCharacterEncoding("UTF-8");
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (NoSuchElementException | SQLException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
