package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Skill {
    @JsonProperty("name")
    private SkillName skillName;
    @JsonProperty("point")
    private int points;
    @JsonIgnore
    private ArrayList<String> endorsedBy;

    public Skill() {}

    public Skill(SkillName skillName, int points) {
        this.skillName = skillName;
        this.points = points;
        this.endorsedBy = new ArrayList<>();
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

    public boolean isEndorsedBy(String userId) {
        for (String id : endorsedBy) {
            if (id.equals(userId))
                return true;
        }
        return false;
    }

    public void endorse(String userId) throws IllegalArgumentException {
        if (isEndorsedBy(userId)) {
            throw new IllegalArgumentException("");
        }
        endorsedBy.add(userId);
        points += 1;
    }
}
