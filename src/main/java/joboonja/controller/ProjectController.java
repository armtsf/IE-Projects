package joboonja.controller;

import joboonja.models.Project;
import joboonja.models.User;
import joboonja.DTO.BidDTO;
import joboonja.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import joboonja.service.ProjectService;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<Project>> getProjects(@RequestAttribute("user") User user, @RequestParam(value = "start", required = false) Integer start, @RequestParam(name = "q", required = false) String query, @RequestParam(name = "offset", required = false) Integer offset) throws SQLException {
        int startValue = 0;
        int offsetValue = 10;
        if (start != null) {
            startValue = start;
        }
        if (offset != null) {
            offsetValue = offset;
        }
        if (query == null) {
            return new ResponseEntity<>(projectService.getProjectsList(user, startValue, offsetValue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(projectService.getSearchResult(query, startValue, offsetValue), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectDetails(@RequestAttribute("user") User user, @PathVariable("id") String id)
            throws IllegalAccessException, NoSuchElementException, SQLException {
        Project project = projectService.getProject(user, id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping("/{id}/bid")
    public ResponseEntity<ResponseMessage> bidProject(@RequestAttribute("user") User user, @PathVariable("id") String id,
                                                      @RequestBody BidDTO bidDTO) throws InvalidObjectException, IllegalAccessException, SQLException {
        long bidAmount = bidDTO.getBidAmount();
        projectService.addBid(user, id, bidAmount);
        ResponseMessage responseMessage = new ResponseMessage(new Date(), "ok");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/{id}/bid")
    public ResponseEntity<BidDTO> getBid(@RequestAttribute("user") User user, @PathVariable("id") String id) throws IllegalAccessException, SQLException {
        BidDTO bidDTO = projectService.getBid(user, id);
        return new ResponseEntity<>(bidDTO, HttpStatus.OK);
    }

}
