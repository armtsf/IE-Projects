package joboonja.service;

import joboonja.models.SkillName;
import joboonja.models.SkillNameList;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class SkillNameService {

    private SkillNameList skillNameList;

    public SkillNameService() throws SQLException {
        this.skillNameList = new SkillNameList();
    }

    public ArrayList<SkillName> getSkillNameList() {
        return skillNameList.all();
    }
}
