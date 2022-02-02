package repositories.DBInteractor;

import properties.AppProperties;

import java.sql.*;

public class PostgreInteractor implements DatabaseInteractor {
    private Connection conn;
    private static PostgreInteractor instance;

    private PostgreInteractor() throws SQLException {
        String url = AppProperties.getProperty("dbConnectionUrl");
        this.conn = DriverManager.getConnection(url);
    }

    public static PostgreInteractor getInstance() throws SQLException {
        if (instance == null) {
            instance = new PostgreInteractor();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
