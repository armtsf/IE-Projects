package joboonja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectSkill {
    @JsonIgnore
    private int id;
    @JsonProperty("name")
    private SkillName skillName;
    @JsonProperty("point")
    private int points;
    @JsonIgnore
    Project project;

    public ProjectSkill() {}

    public void setSkillName(SkillName skillName) {
        this.skillName = skillName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getPoints() {
        return points;
    }

    public SkillName getSkillName() {
        return skillName;
    }

    public Project getProject() {
        return project;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
