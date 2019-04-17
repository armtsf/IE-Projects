package joboonja.models;

public class Bid {
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

    public boolean isValid() {
        if (bidAmount > project.getBudget()) {
            return false;
        }
        return user.isEligibleFor(project);
    }
}
