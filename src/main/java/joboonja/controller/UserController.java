package joboonja.controller;

import joboonja.DTO.*;
import joboonja.models.User;
import joboonja.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.UserService;

import java.io.InvalidObjectException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authService;

    @PostMapping({"/", ""})
    public ResponseEntity<LoginResponseDTO> signUp(@RequestBody UserSignUpDTO dto) throws NoSuchAlgorithmException,
            SQLException, InvalidObjectException, IllegalAccessException {
        return new ResponseEntity<>(authService.signUp(dto), HttpStatus.CREATED);
    }

    @PostMapping({"/login"})
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) throws IllegalAccessException,
            NoSuchAlgorithmException, SQLException {
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }

    @GetMapping({"/", ""})
    public ResponseEntity<List<User>> getUserList(@RequestAttribute("user") User user, @RequestParam(name = "q",
            required = false) String query) throws SQLException {
        if (query != null) {
            return new ResponseEntity<>(userService.getSearchResult(user, query), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.getUsersList(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@RequestAttribute("user") User user, @PathVariable("id") int id) throws SQLException {
        return new ResponseEntity<>(userService.getUser(user, id), HttpStatus.OK);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<List<UserSkillDTO>> getSkills(@RequestAttribute("user") User user, @PathVariable("id") int id) throws SQLException {
        ArrayList<UserSkillDTO> skills = userService.getSkills(user, id);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> addSkill(@RequestAttribute("user") User user, @PathVariable("id") int id,
                                                    @RequestBody SkillNameDTO skillNameDTO) throws IllegalAccessException, SQLException {
        if (!(user.getId() == id)) {
            throw new IllegalAccessException("");
        }
        String skillName = skillNameDTO.getSkillName();
        userService.addSkill(user, skillName.trim());
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> deleteSkill(@RequestAttribute("user") User user, @PathVariable("id") int id,
                                                       @RequestParam(name="skill-name") String skillName) throws IllegalAccessException, SQLException {
        if (!(user.getId() == id)) {
            throw new IllegalAccessException("");
        }
        userService.deleteSkill(user, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills/endorsements")
    public ResponseEntity<ResponseMessage> endorseSkill(@RequestAttribute("user") User user, @PathVariable("id") int id,
                                                        @RequestParam(name="skill-name") String skillName) throws SQLException {
        userService.endorse(user, id, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
