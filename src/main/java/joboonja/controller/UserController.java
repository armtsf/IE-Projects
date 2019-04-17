package joboonja.controller;

import joboonja.models.SkillDto;
import joboonja.models.User;
import joboonja.utils.AddSkillRequest;
import joboonja.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<User>> getUserList(@RequestAttribute("user") User user) {
        return new ResponseEntity<>(userService.getUsersList(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@RequestAttribute("user") User user, @PathVariable("id") String id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<List<SkillDto>> getSkills(@RequestAttribute("user") User user, @PathVariable("id") String id) {
        ArrayList<SkillDto> skills = userService.getSkills(userService.getUser(id), user);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> addSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                    @RequestBody AddSkillRequest addSkillRequest) {
        String skillName = addSkillRequest.getSkillName();
        userService.addSkill(user, skillName.trim());
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> deleteSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                       @RequestParam(name="skill-name") String skillName) {
        userService.deleteSkill(user, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills/endorsements")
    public ResponseEntity<ResponseMessage> endorseSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                        @RequestParam(name="skill-name") String skillName) {
        System.out.println(skillName);
        userService.endorse(user.getId(), id, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
