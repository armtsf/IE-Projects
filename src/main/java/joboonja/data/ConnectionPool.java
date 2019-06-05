package joboonja.data;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {
    private static final BasicDataSource ds = new BasicDataSource();
//    private static final String dbURL = "jdbc:sqlite:joboonja.db";
    private static final String dbURL = "jdbc:mysql://db:3306/joboonja";

    static {
        ds.setUrl(dbURL);
        ds.setUsername("root");
        ds.setPassword("secret");
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException, InterruptedException {
        SQLException exception = new SQLException();
        for (int i = 0; i < 5; i++) {
            try {
                return ds.getConnection();
            } catch (SQLException e) {
                exception = e;
                TimeUnit.SECONDS.sleep(1);
            }
        }
        throw exception;
    }
}