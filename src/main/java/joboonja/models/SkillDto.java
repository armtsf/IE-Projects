package joboonja.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillDto {
    private SkillName name;
    @JsonProperty("point")
    private int points;
    private boolean isEndorsed;

    public SkillDto(SkillName name, int points, boolean isEndorsed) {
        this.name = name;
        this.points = points;
        this.isEndorsed = isEndorsed;
    }

    public SkillName getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public boolean getIsEndorsed() {
        return isEndorsed;
    }
}
