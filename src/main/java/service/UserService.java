package service;

import com.sun.javaws.exceptions.InvalidArgumentException;
import models.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserService {

    public static User getUser(String userId) {
        User requestedUser = UserList.get(userId);
        return requestedUser;
    }

    public static ArrayList<User> getUsersList(User user) {
        ArrayList<User> userList = UserList.getAllExcept(user);
        return userList;
    }

    public static ArrayList<SkillDto> getSkills(User user, User currentUser) {
        ArrayList<Skill> skills = user.getSkills();
        ArrayList<SkillDto> dto = new ArrayList<>();
        for (Skill skill : skills) {
            SkillDto tmpSkill = new SkillDto(skill.getSkillName().getName(), skill.getPoints(),
                    skill.isEndorsedBy(currentUser.getId()));
            dto.add(tmpSkill);
        }
        return dto;
    }

    public static void endorse(String userId, String endorsee, String skillName) throws IllegalArgumentException {
        User user = UserList.get(endorsee);
        Skill skill = user.getSkill(SkillNameList.get(skillName));
        skill.endorse(userId);
    }

    public static void deleteSkill(User user, String skillName) {
        user.deleteSkill(skillName);
    }

    public static void addSkill(User user, String skill) {


    }

}
