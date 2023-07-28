package com.example.java_ignite_8th_day.Database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final static String CONNECTION_URL = "jdbc:mysql://localhost:3306/test";
    private static final HikariDataSource ds;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(CONNECTION_URL);
        config.setUsername("root");
        config.setPassword("12341234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
