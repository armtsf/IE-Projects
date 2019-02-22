package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill {
    @JsonProperty("name")
    private SkillName skillName;
    @JsonProperty("point")
    private int points;

    public Skill() {}

    public Skill(SkillName skillName, int points) {
        this.skillName = skillName;
        this.points = points;
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
}
