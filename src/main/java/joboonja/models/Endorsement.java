package joboonja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Endorsement {
    @JsonIgnore
    private int id;
    private UserSkill userSkill;
    private User fromUser;

    public Endorsement() {}

    Endorsement(UserSkill userSkill, User fromUser) {
        this.userSkill = userSkill;
        this.fromUser = fromUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public UserSkill getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(UserSkill userSkill) {
        this.userSkill = userSkill;
    }
}
