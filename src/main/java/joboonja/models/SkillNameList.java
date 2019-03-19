package joboonja.models;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SkillNameList {
    private static ArrayList<SkillName> skillNames = new ArrayList<>();

    public static void add(SkillName skillName) throws InvalidObjectException {
        for (SkillName s: skillNames) {
            if (s.getName().equals(skillName.getName())) {
                throw new InvalidObjectException("skill name with the same name exists");
            }
        }
        skillNames.add(skillName);
    }

    public static SkillName get(final String name) {
        for (SkillName skillName: skillNames) {
            if (skillName.getName().equals(name)) {
                return skillName;
            }
        }
        throw new NoSuchElementException();
    }

    public static ArrayList<SkillName> all() { return skillNames; }
}
