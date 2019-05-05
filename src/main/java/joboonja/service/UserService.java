package joboonja.service;

import joboonja.DTO.UserDTO;
import joboonja.DTO.UserSkillDTO;
import joboonja.models.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class UserService {

    // user always is the current logged in user, requested user is the user which operations requested on it

    private SkillNameList skillNameList;
    private UserList userList;

    public UserService() throws SQLException {
        this.skillNameList = new SkillNameList();
        this.userList = new UserList();
    }

    public UserDTO getUser(User user, String requestedUserID) throws SQLException {
        User requestedUser = userList.get(requestedUserID);
        UserDTO dto = new UserDTO(requestedUser);
        dto.setSkills(getSkills(user, requestedUserID));
        return dto;
    }

    public ArrayList<User> getUsersList(User user) {
        return userList.getAllExcept(user);
    }

    public ArrayList<UserSkillDTO> getSkills(User user, String requestedUserID) throws SQLException {
        User requestedUser = userList.get(requestedUserID);
        ArrayList<UserSkill> skills = requestedUser.getSkills();
        ArrayList<UserSkillDTO> dto = new ArrayList<>();
        for (UserSkill skill : skills) {
            UserSkillDTO tmpSkill = new UserSkillDTO(skill.getSkillName(), skill.getPoints(), skill.isEndorsedBy(user));
            dto.add(tmpSkill);
        }
        return dto;
    }

    public void endorse(User user, String requestedUserID, String skillName) throws IllegalArgumentException, SQLException {
        User requestedUser = userList.get(requestedUserID);
        UserSkill skill = requestedUser.getSkill(skillNameList.get(skillName));
        skill.endorse(user);
    }

    public void deleteSkill(User user, String skillName) {
        user.deleteSkill(skillName);
    }

    public void addSkill(User user, String skill) throws SQLException {
        user.addSkill(skillNameList.get(skill));
    }

}
