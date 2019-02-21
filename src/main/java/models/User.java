package models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private ArrayList<Skill> skills;
    private String bio;

    public User() {}

    public User(String id, ArrayList<Skill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
