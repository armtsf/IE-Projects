package joboonja.models;

import joboonja.data.mappers.SkillNameMapper;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SkillNameList {
    private SkillNameMapper skillNameMapper;

    public SkillNameList() throws SQLException {
        this.skillNameMapper = new SkillNameMapper();
    }

    public void add(SkillName skillName) throws InvalidObjectException, SQLException {
        if (skillNameMapper.get(skillName.getName()) != null) {
            throw new InvalidObjectException("skill name with the same name exists");
        }
        else {
            skillNameMapper.insert(skillName);
        }
    }

    public SkillName get(final String name) throws SQLException {
        SkillName skillName = skillNameMapper.get(name);
        if (skillName == null)
            throw new NoSuchElementException("no such SkillName");
        else
            return skillName;
    }

    //TODO
    public ArrayList<SkillName> all() { return null; }
}
