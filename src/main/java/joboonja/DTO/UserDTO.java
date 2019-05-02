package joboonja.DTO;

import joboonja.models.User;

import java.util.ArrayList;

public class UserDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private ArrayList<UserSkillDTO> skills = new ArrayList<>();
    private String bio;

    public UserDTO() {}

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.jobTitle = user.getJobTitle();
        this.profilePictureURL = user.getProfilePictureURL();
        this.bio = user.getBio();
    }

    public void setSkills(ArrayList<UserSkillDTO> skills) {
        this.skills = skills;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public ArrayList<UserSkillDTO> getSkills() {
        return skills;
    }

    public String getBio() {
        return bio;
    }
}
