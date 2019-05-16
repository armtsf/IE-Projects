package joboonja.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
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
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getServletPath();
        String method = request.getMethod();
        if (path.equals("/users/login") || (path.equals("/users") && method.equals("POST"))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String authzHeader = request.getHeader("Authorization");
        if (authzHeader == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
            String[] parts = authzHeader.split(" ");
            if (parts.length < 2) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                String token = parts[1];
                try {
                    UserRepository userRepository = new UserRepository();
                    Algorithm algorithm = Algorithm.HMAC256("joboonja");
                    JWTVerifier verifier = JWT.require(algorithm).withIssuer("joboonja").build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    User user = userRepository.get(decodedJWT.getClaim("userId").asInt());
                    servletRequest.setAttribute("user", user);
                    servletRequest.setCharacterEncoding("UTF-8");
                    filterChain.doFilter(servletRequest, servletResponse);
                } catch (SQLException | JWTVerificationException | NoSuchElementException e) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }
}
