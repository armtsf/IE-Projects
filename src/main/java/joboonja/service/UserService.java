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

    private SkillNameRepository skillNameRepository;
    private UserRepository userRepository;

    public UserService() throws SQLException {
        this.skillNameRepository = new SkillNameRepository();
        this.userRepository = new UserRepository();
    }

    public UserDTO getUser(User user, String requestedUserID) throws SQLException {
        User requestedUser = userRepository.get(requestedUserID);
        UserDTO dto = new UserDTO(requestedUser);
        dto.setSkills(getSkills(user, requestedUserID));
        return dto;
    }

    public ArrayList<User> getUsersList(User user) throws SQLException {
        return userRepository.getAllExcept(user);
    }

    public ArrayList<UserSkillDTO> getSkills(User user, String requestedUserID) throws SQLException {
        User requestedUser = userRepository.get(requestedUserID);
        ArrayList<UserSkill> skills = requestedUser.getSkills();
        ArrayList<UserSkillDTO> dto = new ArrayList<>();
        for (UserSkill skill : skills) {
            skill.setEndorsements(userRepository.getEndorsements(skill));
            UserSkillDTO tmpSkill = new UserSkillDTO(skill.getSkillName(), skill.getPoints(), skill.isEndorsedBy(user));
            dto.add(tmpSkill);
        }
        return dto;
    }

    public void endorse(User user, String requestedUserID, String skillName) throws IllegalArgumentException, SQLException {
        User requestedUser = userRepository.get(requestedUserID);
        UserSkill userSkill = requestedUser.getSkill(skillNameRepository.get(skillName));
        userSkill.setEndorsements(userRepository.getEndorsements(userSkill));
        userRepository.endorse(userSkill.endorse(user));
    }

    public void deleteSkill(User user, String skillName) throws SQLException {
        userRepository.deleteSkill(user.deleteSkill(skillName));
    }

    public void addSkill(User user, String skillName) throws SQLException {
        userRepository.addSkill(user.addSkill(skillNameRepository.get(skillName)));
    }

    public ArrayList<User> getSearchResult(User user, String query) throws SQLException {
        return userRepository.searchUsers(user, query);
    }
}
