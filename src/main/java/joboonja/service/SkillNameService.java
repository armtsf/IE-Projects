package joboonja.service;

import joboonja.models.SkillName;
import joboonja.models.SkillNameRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class SkillNameService {

    private SkillNameRepository skillNameRepository;

    public SkillNameService() throws SQLException {
        this.skillNameRepository = new SkillNameRepository();
    }

    public ArrayList<SkillName> getSkillNameList() throws SQLException {
        return skillNameRepository.all();
    }
}
