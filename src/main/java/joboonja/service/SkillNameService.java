package joboonja.service;

import joboonja.models.SkillName;
import joboonja.models.SkillNameList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SkillNameService {
    public ArrayList<SkillName> getSkillNameList() {
        return SkillNameList.all();
    }
}
