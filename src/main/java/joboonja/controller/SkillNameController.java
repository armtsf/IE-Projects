package joboonja.controller;

import joboonja.models.SkillName;
import joboonja.service.SkillNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/skill-names")
public class SkillNameController {

    @Autowired
    private SkillNameService skillNameService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<SkillName>> getSkillNames() throws SQLException {
        return new ResponseEntity<>(skillNameService.getSkillNameList(), HttpStatus.OK);
    }
}
