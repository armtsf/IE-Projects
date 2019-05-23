package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.Project;

import java.sql.*;
import java.util.ArrayList;

public class ProjectMapper extends Mapper<Project> {

    private ProjectSkillMapper projectSkillMapper;
    private UserMapper userMapper;

    public ProjectMapper() throws SQLException {
        this.userMapper = new UserMapper();

        String sql = "CREATE TABLE IF NOT EXISTS Project ("
                + "id VARCHAR(256), "
                + "title VARCHAR(256), "
                + "description TEXT,"
                + "imageURL TEXT, "
                + "budget INTEGER, "
                + "deadline LONG, "
                + "creationDate LONG, "
                + "winnerId INTEGER,"
                + "finished INTEGER,"
                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (winnerId) REFERENCES User(id))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
        this.projectSkillMapper = new ProjectSkillMapper();
    }

    public int insert(Project project) throws SQLException {
        String sql = "INSERT INTO Project VALUES (?, ?, ?, ?, ?, ?, ?, NULL, ?)";
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
            stmt.setBoolean(8, project.isFinished());
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
            project.setWinner(userMapper.get(rs.getInt(8)));
        }
        project.setFinished(rs.getBoolean(9));
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

    public ArrayList<Project> filterUnfinishedReachedDeadline(long now) throws SQLException {
        String sql = "SELECT * FROM Project WHERE finished = ? AND deadline < ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setBoolean(1, false);
            stmt.setLong(2, now);
            return executeFilter(stmt);
        }
    }

    public ArrayList<Project> all(int start, int offset) throws SQLException {
        String countSql = "SELECT COUNT(id) FROM Project";
        String sql = "SELECT * FROM Project ORDER BY creationDate DESC LIMIT ? OFFSET ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement countStmt = conn.prepareStatement(countSql);
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = countStmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count <= start)
                    return new ArrayList<>();
            }

            stmt.setInt(1, offset);
            stmt.setInt(2, start);
            return executeFilter(stmt);
        }
    }

    public ArrayList<Project> search(String query, int start, int offset) throws SQLException {
        String countSql = "SELECT COUNT(id) FROM Project WHERE title LIKE ? OR description LIKE ?";
        String sql = "SELECT * FROM Project WHERE title LIKE ? OR description LIKE ? ORDER BY creationDate DESC LIMIT ? OFFSET ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement countStmt = conn.prepareStatement(countSql);
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            countStmt.setString(1, "%" + query + "%");
            countStmt.setString(2, "%" + query + "%");
            ResultSet rs = countStmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count <= start)
                    return new ArrayList<>();
            }

            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            stmt.setInt(3, offset);
            stmt.setInt(4, start);
            return executeFilter(stmt);
        }
    }

    public int updateWinner(Project project) throws SQLException {
        String sql = "UPDATE Project SET winnerId = ? WHERE id = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, project.getWinner().getId());
            stmt.setString(2, project.getId());
            return stmt.executeUpdate();
        }
    }

    public int updateFinished(Project project) throws SQLException {
        String sql = "UPDATE Project SET finished = ? WHERE id = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setBoolean(1, project.isFinished());
            stmt.setString(2, project.getId());
            return stmt.executeUpdate();
        }
    }
}