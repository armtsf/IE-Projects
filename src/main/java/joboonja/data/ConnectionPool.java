package joboonja.data;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final BasicDataSource ds = new BasicDataSource();
//    private static final String dbURL = "jdbc:sqlite:joboonja.db";
    private static final String dbURL = "jdbc:mysql://localhost:3306/joboonja";

    static {
        ds.setUrl(dbURL);
        ds.setUsername("root");
        ds.setPassword("secret");
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}