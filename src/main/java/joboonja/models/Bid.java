package joboonja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bid {
    @JsonIgnore
    private int id;
    private User user;
    private Project project;
    private long bidAmount;

    public Bid() {}

    public Bid(User user, Project project, long bidAmount) {
        this.user = user;
        this.project = project;
        this.bidAmount = bidAmount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public long getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        if (bidAmount < 0) {
            return false;
        }
        if (bidAmount > project.getBudget()) {
            return false;
        }
        // TODO: check project deadline
        return user.isEligibleFor(project);
    }
}
