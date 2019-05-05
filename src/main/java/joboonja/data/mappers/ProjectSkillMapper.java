package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.ProjectSkill;

import java.sql.*;
import java.util.ArrayList;

public class ProjectSkillMapper {

    private SkillNameMapper skillNameMapper;

    public ProjectSkillMapper() throws SQLException {
        skillNameMapper = new SkillNameMapper();

        String sql = "CREATE TABLE IF NOT EXISTS ProjectSkill ("
                + "points INTEGER,"
                + "FOREIGN KEY (skillName) REFERENCES SkillName(name), "
                + "FOREIGN KEY (projectId) REFERENCES Project(id),"
                + "PRIMARY KEY (skillName, projectId))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(ProjectSkill projectSkill) throws SQLException {
        String sql = "INSERT INTO ProjectSkill VALUES (?, ?, ?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, projectSkill.getPoints());
            stmt.setString(2, projectSkill.getSkillName().getName());
            stmt.setString(3, projectSkill.getProject().getId());
            return stmt.executeUpdate();
        }
    }

    private ProjectSkill load(ResultSet rs) throws SQLException {
        ProjectSkill projectSkill = new ProjectSkill();
        projectSkill.setPoints(rs.getInt(1));
        projectSkill.setSkillName(skillNameMapper.get(rs.getString(2)));
        return projectSkill;
    }

    public ArrayList<ProjectSkill> get(String projectId) throws SQLException {
        ArrayList<ProjectSkill> skills = new ArrayList<>();
        String sql = "SELECT * FROM ProjectSkill WHERE projectId = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, projectId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                skills.add(load(resultSet));
            }
        }
        return skills;
    }
}
