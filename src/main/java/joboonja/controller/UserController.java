package joboonja.controller;

import joboonja.DTO.UserDTO;
import joboonja.DTO.UserSkillDTO;
import joboonja.models.User;
import joboonja.DTO.SkillNameDTO;
import joboonja.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<User>> getUserList(@RequestAttribute("user") User user, @RequestParam(name = "q", required = false) String query) throws SQLException {
        if (query != null)
            return new ResponseEntity<>(userService.getSearchResult(query), HttpStatus.OK);
        return new ResponseEntity<>(userService.getUsersList(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@RequestAttribute("user") User user, @PathVariable("id") String id) throws SQLException {
        return new ResponseEntity<>(userService.getUser(user, id), HttpStatus.OK);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<List<UserSkillDTO>> getSkills(@RequestAttribute("user") User user, @PathVariable("id") String id) throws SQLException {
        ArrayList<UserSkillDTO> skills = userService.getSkills(user, id);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> addSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                    @RequestBody SkillNameDTO skillNameDTO) throws IllegalAccessException, SQLException {
        if (!user.getId().equals(id)) {
            throw new IllegalAccessException("");
        }
        String skillName = skillNameDTO.getSkillName();
        userService.addSkill(user, skillName.trim());
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> deleteSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                       @RequestParam(name="skill-name") String skillName) throws IllegalAccessException, SQLException {
        if (!user.getId().equals(id)) {
            throw new IllegalAccessException("");
        }
        userService.deleteSkill(user, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills/endorsements")
    public ResponseEntity<ResponseMessage> endorseSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                        @RequestParam(name="skill-name") String skillName) throws SQLException {
        userService.endorse(user, id, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
