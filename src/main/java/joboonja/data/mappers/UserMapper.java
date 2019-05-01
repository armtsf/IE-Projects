package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.User;

import java.sql.*;
import java.util.NoSuchElementException;

public class UserMapper {
    public UserMapper() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS User ("
                + "id VARCHAR(256) PRIMARY KEY, "
                + "firstName VARCHAR(256), "
                + "lastName VARCHAR(256), "
                + "jobTitle VARCHAR(256), "
                + "profilePictureURL TEXT, "
                + "bio TEXT)";
        Connection conn = ConnectionPool.getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public int insert(User user) throws SQLException {
        String sql = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getFirstName());
        stmt.setString(3, user.getLastName());
        stmt.setString(4, user.getJobTitle());
        stmt.setString(5, user.getProfilePictureURL());
        stmt.setString(6, user.getBio());
        return stmt.executeUpdate();
    }

    public User load(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString(1));
        user.setFirstName(rs.getString(2));
        user.setLastName(rs.getString(3));
        user.setJobTitle(rs.getString(4));
        user.setProfilePictureURL(rs.getString(5));
        user.setBio(rs.getString(6));
        return user;
    }

    public User find(String id) throws SQLException, NoSuchElementException {
        String sql = "SELECT * FROM User WHERE id = ?";
        Connection conn = ConnectionPool.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet resultSet = stmt.executeQuery();
        if (resultSet.next()) {
            return load(resultSet);
        }
        throw new NoSuchElementException();
    }
}
