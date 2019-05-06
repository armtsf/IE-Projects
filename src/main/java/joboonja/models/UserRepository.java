package joboonja.models;

import joboonja.data.mappers.EndorsementMapper;
import joboonja.data.mappers.UserMapper;
import joboonja.data.mappers.UserSkillMapper;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class UserRepository {
    private UserMapper userMapper;
    private UserSkillMapper userSkillMapper;
    private EndorsementMapper endorsementMapper;

    public UserRepository() throws SQLException {
        this.userMapper = new UserMapper();
        this.userSkillMapper = new UserSkillMapper();
        this.endorsementMapper = new EndorsementMapper();
    }

    public void add(User user) throws InvalidObjectException, SQLException {
        if (userMapper.get(user.getId()) != null) {
            throw new InvalidObjectException("user with the same id exists");
        }
        else {
            userMapper.insert(user);
        }
    }

    public void addSkill(UserSkill userSkill) throws SQLException {
        userSkillMapper.insert(userSkill);
    }

    public void deleteSkill(UserSkill userSkill) throws SQLException {
        userSkillMapper.delete(userSkill);
    }

    public void endorse(Endorsement endorsement) throws SQLException {
        endorsementMapper.insert(endorsement);
    }

    public ArrayList<Endorsement> getEndorsements(UserSkill userSkill) throws SQLException {
        return endorsementMapper.filter(userSkill);
    }

    public User get(final String userId) throws SQLException {
        User user = userMapper.get(userId);
        if (user == null) {
            throw new NoSuchElementException();
        }
        return user;
    }

    public ArrayList<User> getAllExcept(User user) throws SQLException {
        return userMapper.filterAllExcept(user);
    }
}