package models;

import java.util.NoSuchElementException;

public class Bid {
    private String biddingUser;
    private String projectTitle;
    private long bidAmount;

    public Bid() {}

    public Bid(String biddingUser, String projectTitle, long bidAmount) {
        this.biddingUser = biddingUser;
        this.projectTitle = projectTitle;
        this.bidAmount = bidAmount;
    }

    public String getBiddingUser() {
        return biddingUser;
    }

    public void setBiddingUser(String biddingUser) {
        this.biddingUser = biddingUser;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public long getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(long bidAmount) {
        this.bidAmount = bidAmount;
    }

    public boolean isValid() {
        Project project;
        try {
            project = ProjectList.get(projectTitle);
        } catch (NoSuchElementException e) {
            return false;
        }
        if (bidAmount > project.getBudget()) {
            return false;
        }

        User user;
        try {
            user = UserList.get(biddingUser);
        } catch (NoSuchElementException e) {
            return false;
        }
        for (Skill skill: project.getSkills()) {
            try {
                Skill userSkill = user.getSkill(skill.getSkillName());
                if (userSkill.getPoints() < skill.getPoints()) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }
}
