package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.Project;
import joboonja.models.ProjectSkill;

import java.sql.*;
import java.util.ArrayList;

public class ProjectSkillMapper extends Mapper<ProjectSkill> {

    private SkillNameMapper skillNameMapper;

    public ProjectSkillMapper() throws SQLException {
        skillNameMapper = new SkillNameMapper();

        String sql = "CREATE TABLE IF NOT EXISTS ProjectSkill ("
                + "id INTEGER AUTO_INCREMENT,"
                + "points INTEGER, "
                + "skillNameId INTEGER, "
                + "projectId VARCHAR(256), "
                + "FOREIGN KEY (skillNameId) REFERENCES SkillName(id), "
                + "FOREIGN KEY (projectId) REFERENCES Project(id),"
                + "PRIMARY KEY (id), "
                + "UNIQUE (skillNameId, projectId))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(ProjectSkill projectSkill) throws SQLException {
        String sql = "INSERT INTO ProjectSkill (points, skillNameId, projectId) VALUES (?, ?, ?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, projectSkill.getPoints());
            stmt.setInt(2, projectSkill.getSkillName().getId());
            stmt.setString(3, projectSkill.getProject().getId());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected ProjectSkill load(ResultSet rs) throws SQLException {
        ProjectSkill projectSkill = new ProjectSkill();
        projectSkill.setId(rs.getInt(1));
        projectSkill.setPoints(rs.getInt(2));
        projectSkill.setSkillName(skillNameMapper.get(rs.getInt(3)));
        return projectSkill;
    }

    public ArrayList<ProjectSkill> get(Project project) throws SQLException {
        ArrayList<ProjectSkill> skills = new ArrayList<>();
        String sql = "SELECT * FROM ProjectSkill WHERE projectId = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, project.getId());
            ArrayList<ProjectSkill> res = executeFilter(stmt);
            for (ProjectSkill projectSkill: res) {
                projectSkill.setProject(project);
            }
            return res;
        }
    }
}
