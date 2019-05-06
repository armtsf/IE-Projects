package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.User;
import joboonja.models.UserSkill;

import java.sql.*;
import java.util.ArrayList;

public class UserSkillMapper extends Mapper<UserSkill> {

    private SkillNameMapper skillNameMapper;

    public UserSkillMapper() throws SQLException {
        skillNameMapper = new SkillNameMapper();

        String sql = "CREATE TABLE IF NOT EXISTS UserSkill ("
                + "id INTEGER PRIMARY KEY, "
                + "points INTEGER, "
                + "skillNameId INTEGER, "
                + "userId VARCHAR(256), "
                + "FOREIGN KEY (skillName) REFERENCES SkillName(id), "
                + "FOREIGN KEY (userId) REFERENCES User(id),"
                + "UNIQUE (skillName, userId))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(UserSkill userSkill) throws SQLException {
        String sql = "INSERT INTO UserSkill VALUES (NULL, ?, ?, ?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userSkill.getPoints());
            stmt.setString(2, userSkill.getSkillName().getName());
            stmt.setString(3, userSkill.getUser().getId());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected UserSkill load(ResultSet rs) throws SQLException {
        UserSkill userSkill = new UserSkill();
        userSkill.setPoints(rs.getInt(1));
        userSkill.setSkillName(skillNameMapper.get(rs.getString(2)));
        return userSkill;
    }

    public ArrayList<UserSkill> filter(User user) throws SQLException {
        String sql = "SELECT * FROM UserSkill WHERE userId = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getId());
            ArrayList<UserSkill> res = executeFilter(stmt);
            for (UserSkill userSkill: res) {
                userSkill.setUser(user);
            }
            return res;
        }
    }
}
