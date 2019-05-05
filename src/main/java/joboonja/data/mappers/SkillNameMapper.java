package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.SkillName;

import java.sql.*;

public class SkillNameMapper {
    public SkillNameMapper() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS SkillName ("
                + "name VARCHAR(256) PRIMARY KEY)";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(SkillName skillName) throws SQLException {
        String sql = "INSERT INTO SkillName VALUES (?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, skillName.getName());
            return stmt.executeUpdate();
        }
    }

    private SkillName load(ResultSet rs) throws SQLException {
        SkillName skillName = new SkillName();
        skillName.setName(rs.getString(1));
        return skillName;
    }

    public SkillName get(String name) throws SQLException {
        String sql = "SELECT * FROM SkillName WHERE name = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return load(resultSet);
            }
        }
        return null;
    }
}
