package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.Project;

import java.sql.*;
import java.util.ArrayList;

public class ProjectMapper extends Mapper<Project> {

    private final int LIMIT = 10;

    private ProjectSkillMapper projectSkillMapper;
    private UserMapper userMapper;

    public ProjectMapper() throws SQLException {
        this.projectSkillMapper = new ProjectSkillMapper();
        this.userMapper = new UserMapper();

        String sql = "CREATE TABLE IF NOT EXISTS Project ("
                + "id VARCHAR(256) PRIMARY KEY, "
                + "title VARCHAR(256), "
                + "description TEXT,"
                + "imageURL TEXT, "
                + "budget INTEGER, "
                + "deadline INTEGER, "
                + "creationDate INTEGER, "
                + "winnerId VARCHAR(256),"
                + "FOREIGN KEY (winnerId) REFERENCES user(id))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(Project project) throws SQLException {
        String sql = "INSERT INTO Project VALUES (?, ?, ?, ?, ?, ?, ?, NULL)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, project.getId());
            stmt.setString(2, project.getTitle());
            stmt.setString(3, project.getDescription());
            stmt.setString(4, project.getImageURL());
            stmt.setLong(5, project.getBudget());
            stmt.setLong(6, project.getDeadline());
            stmt.setLong(7, project.getCreationDate());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected Project load(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setId(rs.getString(1));
        project.setTitle(rs.getString(2));
        project.setDescription(rs.getString(3));
        project.setImageURL(rs.getString(4));
        project.setBudget(rs.getLong(5));
        project.setDeadline(rs.getLong(6));
        project.setCreationDate(rs.getLong(7));
        project.setSkills(projectSkillMapper.get(project));
        if (rs.getString(8) != null) {
            project.setWinner(userMapper.get(rs.getString(8)));
        }
        return project;
    }

    public Project get(String id) throws SQLException {
        String sql = "SELECT * FROM Project WHERE id = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, id);
            return executeGet(stmt);
        }
    }

    public ArrayList<Project> all(int page) throws SQLException {
        String sql = "SELECT * FROM Project ORDER BY creationDate DESC LIMIT " + LIMIT + " OFFSET " + page*LIMIT;
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            return executeFilter(stmt);
        }
    }

    public ArrayList<Project> search(String project, int page) throws SQLException {
        String sql = "SELECT * FROM Project WHERE title LIKE \"%" + project + "%\" OR description LIKE \"%" + project +
                "%\" ORDER BY creationDate DESC LIMIT " + LIMIT + " OFFSET " + page*LIMIT;
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            return executeFilter(stmt);
        }
    }
}