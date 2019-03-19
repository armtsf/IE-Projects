package joboonja.controller;

import joboonja.models.Project;
import joboonja.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.ProjectService;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Project>> getProjects(@RequestAttribute("user") User user) {
        return new ResponseEntity<List<Project>>(projectService.getProjectList(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjecDetails(@RequestAttribute("user") User user, @PathVariable("id") String id)
            throws IllegalAccessException, NoSuchElementException {
        Project project = projectService.getProjectDetails(user, id);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @PostMapping("/{id}/bid")
    public ResponseEntity bidProject(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                     @RequestParam long bidAmount) throws InvalidObjectException {
        projectService.addBid(user, id, bidAmount);
        return new ResponseEntity(HttpStatus.OK);
    }

}
