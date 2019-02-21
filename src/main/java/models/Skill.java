package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill {
    private String name;
    @JsonProperty("point")
    private int points;

    public Skill() {}

    public Skill(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
