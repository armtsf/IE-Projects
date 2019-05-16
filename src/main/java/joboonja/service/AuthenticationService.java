package joboonja.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import joboonja.DTO.LoginRequestDTO;
import joboonja.DTO.LoginResponseDTO;
import joboonja.DTO.UserSignUpDTO;
import joboonja.models.User;
import joboonja.models.UserRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.InvalidObjectException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

@Service
public class AuthenticationService {
    private UserRepository userRepository;

    public AuthenticationService() throws SQLException {
        this.userRepository = new UserRepository();
    }

    private String createJWTToken() {
        Algorithm algorithm = Algorithm.HMAC256("joboonja"); // TODO
        return JWT.create().withIssuer("joboonja")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                .sign(algorithm);
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = password.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bytesOfMessage);
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public LoginResponseDTO login(LoginRequestDTO request) throws SQLException, IllegalAccessException, NoSuchAlgorithmException {
        String username = request.getUsername();
        String password = request.getPassword();
        User user = userRepository.getByUsername(username);
        String passwordHash = hashPassword(password);
        if (user.getPasswordHash().equals(passwordHash)) {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(createJWTToken());
            response.setId(user.getId());
            return response;
        }

        throw new IllegalAccessException();
    }

    public LoginResponseDTO signUp(UserSignUpDTO request) throws SQLException, InvalidObjectException, IllegalAccessException, NoSuchAlgorithmException {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("user with the same username exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPasswordHash(hashPassword(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setProfilePictureURL(request.getProfilePictureURL());
        user.setBio(request.getBio());
        user.setJobTitle(request.getJobTitle());
        userRepository.add(user);

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(createJWTToken());
        response.setId(userRepository.getByUsername(user.getUsername()).getId());
        return response;
    }
}
