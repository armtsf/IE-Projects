package models;

import java.util.ArrayList;

public class Project {
    private String title;
    private ArrayList<Skill> skills;
    private long budget;

    public Project() {}

    public Project(String title, ArrayList<Skill> skills, long budget) {
        this.title = title;
        this.skills = skills;
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
