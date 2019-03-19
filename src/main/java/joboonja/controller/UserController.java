package joboonja.controller;

import joboonja.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public ResponseEntity<List<User>> getUserList(@RequestAttribute("user") User user) {
        return new ResponseEntity<List<User>>(userService.getUsersList(user), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@RequestAttribute("user") User user, @PathVariable("id") String id) {
//
//    }

    @PostMapping("/{id}/skills/{skillName}")
    public ResponseEntity addSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                   @PathVariable("skillName") String skillName) {
        userService.addSkill(user, skillName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/skills/{skillName}")
    public ResponseEntity deleteSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                      @PathVariable("skillName") String skillName) {
        userService.deleteSkill(user, skillName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}/skills/{skillName}/endorsements")
    public ResponseEntity endorseSkill(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                       @PathVariable("skillName") String skillName) {
        userService.endorse(user.getId(), id, skillName);
        return new ResponseEntity(HttpStatus.OK);
    }

}
