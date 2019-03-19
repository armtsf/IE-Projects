package joboonja.models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private ArrayList<Skill> skills;
    private String bio;

    public User() {}

    public User(String id, ArrayList<Skill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public boolean isEligibleFor(Project project) {
        for (Skill skill: project.getSkills()) {
            try {
                Skill userSkill = this.getSkill(skill.getSkillName());
                if (userSkill.getPoints() < skill.getPoints()) {
                    return false;
                }
            } catch (NoSuchElementException e) {
                return false;
            }
        }
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    public Skill getSkill(SkillName skillName) {
        return skills.stream().filter(skill -> skill.getSkillName().getName().equals(skillName.getName())).findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void deleteSkill(String skillName) {
        for (Skill skill : skills) {
            if (skill.getSkillName().getName().equals(skillName)) {
                skills.remove(skill);
                return;
            }
        }
    }

    public void addSkill(String skillName) {
        for (Skill skill: skills) {
            if (skill.getSkillName().getName().equals(skillName))
                throw new IllegalArgumentException();
        }
        skills.add(new Skill(SkillNameList.get(skillName), 0));
    }
}
