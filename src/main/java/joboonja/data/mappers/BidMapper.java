package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.Bid;
import joboonja.models.Project;
import joboonja.models.User;

import java.sql.*;
import java.util.ArrayList;

public class BidMapper extends Mapper<Bid> {
    private UserMapper userMapper;

    public BidMapper() throws SQLException {
        userMapper = new UserMapper();

        String sql = "CREATE TABLE IF NOT EXISTS Bid ("
                + "id INTEGER PRIMARY KEY, "
                + "userId VARCHAR(256), "
                + "projectId VARCHAR(256), "
                + "bidAmount INTEGER, "
                + "FOREIGN KEY (userId) REFERENCES User(id), "
                + "FOREIGN KEY (projectId) REFERENCES Project(id), "
                + "UNIQUE (userId, projectId))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(Bid bid) throws SQLException {
        String sql = "INSERT INTO Bid VALUES (NULL, ?, ?, ?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, bid.getUser().getId());
            stmt.setString(2, bid.getProject().getId());
            stmt.setLong(3, bid.getBidAmount());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected Bid load(ResultSet rs) throws SQLException {
        Bid bid = new Bid();
        bid.setId(rs.getInt(1));
        bid.setUser(userMapper.get(rs.getString(2)));
        bid.setBidAmount(rs.getLong(4));
        return bid;
    }

    public Bid get(User user, Project project) throws SQLException {
        String sql = "SELECT * FROM Bid WHERE userId = ? AND projectID = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, user.getId());
            stmt.setString(2, project.getId());
            Bid bid = executeGet(stmt);
            if (bid != null) {
                bid.setProject(project);
            }
            return bid;
        }
    }

    public ArrayList<Bid> filter(Project project) throws SQLException {
        String sql = "SELECT * FROM Bid WHERE projectID = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, project.getId());
            ArrayList<Bid> res = executeFilter(stmt);
            for (Bid bid: res) {
                bid.setProject(project);
            }
            return res;
        }
    }
}
