package joboonja.models;

import joboonja.data.mappers.UserMapper;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class UserList {
    private UserMapper userMapper;

    public UserList() throws SQLException {
        this.userMapper = new UserMapper();
    }

    public void add(User user) throws InvalidObjectException, SQLException {
        if (userMapper.get(user.getId()) != null) {
            throw new InvalidObjectException("user with the same id exists");
        }
        else {
         userMapper.insert(user);
        }
    }

    public User get(final String userId) throws SQLException {
        User user = userMapper.get(userId);
        if (user == null)
            throw new NoSuchElementException();
        else
            return user;
    }

    //TODO
    public ArrayList<User> getAllExcept(User user) {
        return null;
    }
}
