package joboonja.service;

import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    public User getUser(String userId) {
        User requestedUser = UserList.get(userId);
        return requestedUser;
    }

    public ArrayList<User> getUsersList(User user) {
        ArrayList<User> userList = UserList.getAllExcept(user);
        return userList;
    }

    public ArrayList<SkillDto> getSkills(User user, User currentUser) {
        ArrayList<Skill> skills = user.getSkills();
        ArrayList<SkillDto> dto = new ArrayList<>();
        for (Skill skill : skills) {
            SkillDto tmpSkill = new SkillDto(skill.getSkillName().getName(), skill.getPoints(),
                    skill.isEndorsedBy(currentUser.getId()));
            dto.add(tmpSkill);
        }
        return dto;
    }

    public void endorse(String userId, String endorsee, String skillName) throws IllegalArgumentException {
        User user = UserList.get(endorsee);
        Skill skill = user.getSkill(SkillNameList.get(skillName));
        skill.endorse(userId);
    }

    public void deleteSkill(User user, String skillName) {
        user.deleteSkill(skillName);
    }

    public void addSkill(User user, String skill) {
        user.addSkill(skill);
    }

}
