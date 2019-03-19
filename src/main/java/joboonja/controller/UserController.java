package joboonja.controller;

import joboonja.models.User;
import joboonja.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.UserService;

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

    @PostMapping("/{id}/skills")
    public ResponseEntity<ResponseMessage> addSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                    @RequestParam String skillName) {
        userService.addSkill(user, skillName.trim());
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/skills/{skillName}")
    public ResponseEntity<ResponseMessage> deleteSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                      @PathVariable("skillName") String skillName) {
        userService.deleteSkill(user, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/{id}/skills/{skillName}/endorsements")
    public ResponseEntity<ResponseMessage> endorseSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                       @PathVariable("skillName") String skillName) {
        userService.endorse(user.getId(), id, skillName);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
