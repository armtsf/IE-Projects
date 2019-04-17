package joboonja.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import joboonja.models.SkillName;

public class SkillDTO {
    private SkillName name;
    @JsonProperty("point")
    private int points;
    private boolean isEndorsed;

    public SkillDTO(SkillName name, int points, boolean isEndorsed) {
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
