package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.User;

import java.sql.*;
import java.util.ArrayList;

public class UserMapper extends Mapper<User> {

    private UserSkillMapper userSkillMapper;

    public UserMapper() throws SQLException {
        this.userSkillMapper = new UserSkillMapper();
        String sql = "CREATE TABLE IF NOT EXISTS User ("
                + "id INTEGER PRIMARY KEY, "
                + "firstName VARCHAR(256), "
                + "lastName VARCHAR(256), "
                + "jobTitle VARCHAR(256), "
                + "profilePictureURL TEXT, "
                + "bio TEXT, "
                + "username VARCHAR(256), "
                + "passwordHash VARCHAR(256), "
                + "UNIQUE (username))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO User VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getJobTitle());
            stmt.setString(4, user.getProfilePictureURL());
            stmt.setString(5, user.getBio());
            stmt.setString(6, user.getUsername());
            stmt.setString(7, user.getPasswordHash());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected User load(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setFirstName(rs.getString(2));
        user.setLastName(rs.getString(3));
        user.setJobTitle(rs.getString(4));
        user.setProfilePictureURL(rs.getString(5));
        user.setBio(rs.getString(6));
        user.setUsername(rs.getString(7));
        user.setPasswordHash(rs.getString(8));
        user.setSkills(userSkillMapper.filter(user));
        return user;
    }

    public User get(int id) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            return executeGet(stmt);
        }
    }

    public User getByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, username);
            return executeGet(stmt);
        }
    }

    public ArrayList<User> filterAllExcept(User user) throws SQLException {
        String sql = "SELECT * FROM User WHERE id != ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, user.getId());
            return executeFilter(stmt);
        }
    }

    public ArrayList<User> search(User user, String query) throws SQLException {
        String sql = "SELECT * FROM User WHERE (firstName LIKE ? OR lastName LIKE ?) AND id != ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            stmt.setInt(3, user.getId());
            return executeFilter(stmt);
        }
    }
}
