package models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class User {

    private String username;
    private ArrayList<Skill> skills;

    public User() {}

    public User(String username, ArrayList<Skill> skills) {
        this.username = username;
        this.skills = skills;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Skill getSkill(String skillName) {
        return skills.stream().filter(skill -> skill.getName().equals(skillName)).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}
