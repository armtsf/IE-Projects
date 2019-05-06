package joboonja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SkillName {
    @JsonIgnore
    private int id;
    private String name;

    public SkillName() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public SkillName(String name) {
        this.name = name;
    }
}
