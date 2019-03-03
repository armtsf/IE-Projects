package service;

import models.*;

import java.util.ArrayList;

public class UserService {

    public static User getUser(String userId) {
        User requestedUser = UserList.get(userId);
        return requestedUser;
    }

    public static ArrayList<User> getUsersList(User user) {
        ArrayList<User> userList = UserList.getAllExcept(user);
        return userList;
    }

    public static ArrayList<SkillDto> getSkills(User user) {
        ArrayList<Skill> skills = user.getSkills();
        ArrayList<SkillDto> dto = new ArrayList<>();
        for (Skill skill : skills) {
            SkillDto tmpSkill = new SkillDto(skill.getSkillName().getName(), skill.getPoints(),
                    skill.isEndorsedBy(user.getId()));
            dto.add(tmpSkill);
        }
        return dto;
    }

    public static void endorse(String userId, String endorsee, String skillName) {
        User user = UserList.get(endorsee);
        Skill skill = user.getSkill(SkillNameList.get(skillName));
        skill.endorse(userId);
    }

}
