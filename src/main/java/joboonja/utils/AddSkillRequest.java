package joboonja.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddSkillRequest {
    private String skillName;

    public AddSkillRequest() {}

    public AddSkillRequest(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
