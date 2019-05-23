package joboonja.data.mappers;

import joboonja.data.ConnectionPool;
import joboonja.models.Endorsement;
import joboonja.models.UserSkill;

import java.sql.*;
import java.util.ArrayList;

public class EndorsementMapper extends Mapper<Endorsement> {

    private UserMapper userMapper;

    public EndorsementMapper() throws SQLException {
        userMapper = new UserMapper();

        String sql = "CREATE TABLE IF NOT EXISTS Endorsement ("
                + "id INTEGER AUTO_INCREMENT, "
                + "userSkillId INTEGER, "
                + "fromUserId INTEGER, "
                + "FOREIGN KEY (userSkillId) REFERENCES UserSkill(id) ON DELETE CASCADE, "
                + "FOREIGN KEY (fromUserId) REFERENCES User(id), "
                + "PRIMARY KEY (id),"
                + "UNIQUE (userSkillId, fromUserId))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(Endorsement endorsement) throws SQLException {
        try (
                Connection conn = ConnectionPool.getConnection();
        ) {
            return insert(conn, endorsement);
        }
    }

    public int insert(Connection conn, Endorsement endorsement) throws SQLException {
        String sql = "INSERT INTO Endorsement (userSkillId, fromUserId) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, endorsement.getUserSkill().getId());
            stmt.setInt(2, endorsement.getFromUser().getId());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected Endorsement load(ResultSet rs) throws SQLException {
        Endorsement endorsement = new Endorsement();
        endorsement.setId(rs.getInt(1));
        endorsement.setFromUser(userMapper.get(rs.getInt(3)));
        return endorsement;
    }

    public ArrayList<Endorsement> filter(UserSkill userSkill) throws SQLException {
        String sql = "SELECT * FROM Endorsement WHERE userSkillId = ?";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, userSkill.getId());
            ArrayList<Endorsement> res = executeFilter(stmt);
            for (Endorsement endorsement: res) {
                endorsement.setUserSkill(userSkill);
            }
            return res;
        }
    }
}
