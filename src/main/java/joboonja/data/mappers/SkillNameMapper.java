package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.SkillName;

import java.sql.*;
import java.util.ArrayList;

public class SkillNameMapper extends Mapper<SkillName> {
    public SkillNameMapper() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS SkillName ("
                + "id INTEGER AUTO_INCREMENT, "
                + "name VARCHAR(256) UNIQUE,"
                + "PRIMARY KEY (id));";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(SkillName skillName) throws SQLException {
        String sql = "INSERT INTO SkillName (name) VALUES (?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, skillName.getName());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected SkillName load(ResultSet rs) throws SQLException {
        SkillName skillName = new SkillName();
        skillName.setId(rs.getInt(1));
        skillName.setName(rs.getString(2));
        return skillName;
    }

    public SkillName get(String name) throws SQLException {
        String sql = "SELECT * FROM SkillName WHERE name = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            return executeGet(stmt);
        }
    }

    public SkillName get(int id) throws SQLException {
        String sql = "SELECT * FROM SkillName WHERE id = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            return executeGet(stmt);
        }
    }

    public ArrayList<SkillName> all() throws SQLException {
        String sql = "SELECT * FROM SkillName";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            return executeFilter(stmt);
        }
    }
}
