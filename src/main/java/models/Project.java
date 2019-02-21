package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Project {
    private String id;
    private String title;
    private String description;
    @JsonProperty("imageUrl")
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

    public Project(String id, String title, String description, String imageURL, ArrayList<Skill> skills,
                   long budget, long deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.skills = skills;
        this.budget = budget;
        this.deadline = deadline;
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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getWinner() {
        return winner;
    }
}
