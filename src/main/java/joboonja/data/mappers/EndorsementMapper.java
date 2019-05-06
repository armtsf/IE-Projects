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
                + "id INTEGER PRIMARY KEY, "
                + "userSkillId INTEGER, "
                + "fromUserId VARCHAR(256), "
                + "FOREIGN KEY (userSkillId) REFERENCES UserSkill(id), "
                + "FOREIGN KEY (fromUserId) REFERENCES User(id), "
                + "UNIQUE (userSkillId, fromUserId))";
        try (
                Connection conn = ConnectionPool.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        }
    }

    public int insert(Endorsement endorsement) throws SQLException {
        String sql = "INSERT INTO Endorsement VALUES (NULL, ?, ?)";
        try (
                Connection conn = ConnectionPool.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, endorsement.getUserSkill().getId());
            stmt.setString(2, endorsement.getFromUser().getId());
            return stmt.executeUpdate();
        }
    }

    @Override
    protected Endorsement load(ResultSet rs) throws SQLException {
        Endorsement endorsement = new Endorsement();
        endorsement.setFromUser(userMapper.get(rs.getString(2)));
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
