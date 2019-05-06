package joboonja.data.mappers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Mapper<T> {
    abstract T load(ResultSet rs) throws SQLException;

    protected T executeGet(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return load(rs);
        }
        return null;
    }

    protected ArrayList<T> executeFilter(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        ArrayList<T> res = new ArrayList<>();
        while (rs.next()) {
            res.add(load(rs));
        }
        return res;
    }
}
