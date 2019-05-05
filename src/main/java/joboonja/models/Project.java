package joboonja.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Project {
    private String id;
    private String title;
    private String description;
    @JsonProperty("imageUrl")
    private String imageURL;
    private ArrayList<ProjectSkill> skills;
    private long budget;
    private long deadline;
    private long creationDate;
    private User winner;

    public Project() {}

    public Project(String id, ArrayList<ProjectSkill> skills, long budget) {
        this.id = id;
        this.skills = skills;
        this.budget = budget;
    }

    public Project(String id, String title, String description, String imageURL, ArrayList<ProjectSkill> skills,
                   long budget, long deadline, long creationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.skills = skills;
        this.budget = budget;
        this.deadline = deadline;
        this.creationDate = creationDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ProjectSkill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<ProjectSkill> skills) {
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

    public long getCreationDate() {
        return creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }
}
