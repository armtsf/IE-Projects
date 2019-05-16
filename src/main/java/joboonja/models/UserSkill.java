package joboonja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class UserSkill {
    @JsonIgnore
    private int id;
    @JsonProperty("name")
    private SkillName skillName;
    @JsonProperty("point")
    private int points;
    @JsonIgnore
    private ArrayList<Endorsement> endorsements = new ArrayList<>();
    @JsonIgnore
    private User user;

    public UserSkill() {}

    public UserSkill(SkillName skillName, int points) {
        this.skillName = skillName;
        this.points = points;
    }

    public UserSkill(SkillName skillName, int points, User user) {
        this.skillName = skillName;
        this.points = points;
        this.endorsements = new ArrayList<>();
        this.user = user;
    }

    public SkillName getSkillName() {
        return skillName;
    }

    public void setSkillName(SkillName skillName) {
        this.skillName = skillName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEndorsements(ArrayList<Endorsement> endorsements) {
        this.endorsements = endorsements;
    }

    public ArrayList<Endorsement> getEndorsements() {
        return endorsements;
    }

    public boolean isEndorsedBy(User user) {
        for (Endorsement e: endorsements) {
            if (e.getFromUser().getId() == user.getId()) {
                return true;
            }
        }
        return false;
    }

    public Endorsement endorse(User user) throws IllegalArgumentException {
        if (isEndorsedBy(user)) {
            throw new IllegalArgumentException("cannot endorse a skill twice");
        }
        if (user.getId() == this.getUser().getId()) {
            throw new IllegalArgumentException("you cannot endorse your own skill");
        }
        Endorsement endorsement = new Endorsement(this, user);
        endorsements.add(endorsement);
        points += 1;
        return endorsement;
    }
}
