package joboonja.models;

public class Endorsement {
    private User fromUser;
    private UserSkill userSkill;

    Endorsement() {}

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

    public UserSkill getUserSkill() {
        return userSkill;
    }

    public void setUserSkill(UserSkill userSkill) {
        this.userSkill = userSkill;
    }
}
