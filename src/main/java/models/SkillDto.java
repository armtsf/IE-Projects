package models;

public class SkillDto {
    private String name;
    private int points;
    private boolean isEndorsed;

    public SkillDto(String name, int points, boolean isEndorsed) {
        this.name = name;
        this.points = points;
        this.isEndorsed = isEndorsed;
    }
}