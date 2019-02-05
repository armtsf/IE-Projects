package models;

import java.util.ArrayList;

public class User {
    public User() {}

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

    private String username;
    private ArrayList<Skill> skills;

    public User(String username, ArrayList<Skill> skills) {
        this.username = username;
        this.skills = skills;
    }
}
