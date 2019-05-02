package joboonja.service;

import joboonja.DTO.UserDTO;
import joboonja.DTO.UserSkillDTO;
import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    // user always is the current logged in user, requested user is the user which operations requested on it

    public UserDTO getUser(User user, String requestedUserID) {
        User requestedUser = UserList.get(requestedUserID);
        UserDTO dto = new UserDTO(requestedUser);
        dto.setSkills(getSkills(user, requestedUserID));
        return dto;
    }

    public ArrayList<User> getUsersList(User user) {
        return UserList.getAllExcept(user);
    }

    public ArrayList<UserSkillDTO> getSkills(User user, String requestedUserID) {
        User requestedUser = UserList.get(requestedUserID);
        ArrayList<UserSkill> skills = requestedUser.getSkills();
        ArrayList<UserSkillDTO> dto = new ArrayList<>();
        for (UserSkill skill : skills) {
            UserSkillDTO tmpSkill = new UserSkillDTO(skill.getSkillName(), skill.getPoints(), skill.isEndorsedBy(user));
            dto.add(tmpSkill);
        }
        return dto;
    }

    public void endorse(User user, String requestedUserID, String skillName) throws IllegalArgumentException {
        User requestedUser = UserList.get(requestedUserID);
        UserSkill skill = requestedUser.getSkill(SkillNameList.get(skillName));
        skill.endorse(user);
    }

    public void deleteSkill(User user, String skillName) {
        user.deleteSkill(skillName);
    }

    public void addSkill(User user, String skill) {
        user.addSkill(skill);
    }

}
