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
        userSkillMapper.addEndorsement(endorsement, endorsementMapper);
    }

    public ArrayList<Endorsement> getEndorsements(UserSkill userSkill) throws SQLException {
        return endorsementMapper.filter(userSkill);
    }

    public User get(final int userId) throws SQLException {
        User user = userMapper.get(userId);
        if (user == null) {
            throw new NoSuchElementException();
        }
        return user;
    }

    public User getByUsername(final String username) throws SQLException, IllegalAccessException {
        User user = userMapper.getByUsername(username);
        if (user == null) {
            throw new IllegalAccessException();
        }
        return user;
    }

    public boolean existsByUsername(final String username) throws SQLException {
        User user = userMapper.getByUsername(username);
        return user != null;
    }

    public ArrayList<User> getAllExcept(User user) throws SQLException {
        return userMapper.filterAllExcept(user);
    }

    public ArrayList<User> searchUsers(User user, String query) throws SQLException {
        return userMapper.search(user, query);
    }
}
