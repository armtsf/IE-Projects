package models;

import java.util.ArrayList;

public class Project {
    private String id;
    private String title;
    private String description;
    private String imageURL;
    private ArrayList<Skill> skills;
    private long budget;
    private long deadline;
    private User winner;

    public Project() {}

    public Project(String id, ArrayList<Skill> skills, long budget) {
        this.id = id;
        this.skills = skills;
        this.budget = budget;
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

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }
}
