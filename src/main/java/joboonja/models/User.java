package joboonja.models;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private ArrayList<UserSkill> skills = new ArrayList<>();
    private String bio;

    public User() {}

    public User(String id, ArrayList<UserSkill> skills) {
        this.id = id;
        this.skills = skills;
    }

    public boolean isEligibleFor(Project project) {
        for (ProjectSkill skill: project.getSkills()) {
            try {
                UserSkill userSkill = this.getSkill(skill.getSkillName());
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

    public ArrayList<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<UserSkill> skills) {
        this.skills = skills;
    }

    public UserSkill getSkill(SkillName skillName) {
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

    public UserSkill deleteSkill(String skillName) {
        for (UserSkill skill : skills) {
            if (skill.getSkillName().getName().equals(skillName)) {
                skills.remove(skill);
                return skill;
            }
        }
        throw new NoSuchElementException("no such skill");
    }

    public UserSkill addSkill(SkillName skillName) {
        for (UserSkill skill: skills) {
            if (skill.getSkillName().getName().equals(skillName))
                throw new IllegalArgumentException("duplicate skill for this user");
        }
        UserSkill userSkill = new UserSkill(skillName, 0, this);
        skills.add(userSkill);
        return userSkill;
    }
}
