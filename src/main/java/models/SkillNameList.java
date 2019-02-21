package models;

import java.io.InvalidObjectException;
import java.util.ArrayList;

public class SkillNameList {
    private static ArrayList<SkillName> skillNames = new ArrayList<>();

    public static void add(SkillName skillName) throws InvalidObjectException {
        for (SkillName s: skillNames) {
            if (s.getName().equals(skillName.getName())) {
                throw new InvalidObjectException("user with the same id exists");
            }
        }
        skillNames.add(skillName);
    }
}
