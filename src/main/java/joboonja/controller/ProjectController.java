package joboonja.controller;

import joboonja.models.Project;
import joboonja.models.User;
import joboonja.utils.BidRequest;
import joboonja.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.ProjectService;

import java.io.InvalidObjectException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Project>> getProjects(@RequestAttribute("user") User user) {
        return new ResponseEntity<>(projectService.getProjectList(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectDetails(@RequestAttribute("user") User user, @PathVariable("id") String id)
            throws IllegalAccessException, NoSuchElementException {
        Project project = projectService.getProjectDetails(user, id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/{id}/bid")
    public ResponseEntity<ResponseMessage> bidProject(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                      @RequestBody BidRequest bidRequest) throws InvalidObjectException {
        long bidAmount = bidRequest.getBidAmount();
        projectService.addBid(user, id, bidAmount);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
